package com.my.PJ.chat;

import java.util.HashMap;
import java.util.Set;

import org.json.JSONObject;
import org.springframework.stereotype.Component;

import jakarta.websocket.OnClose;
import jakarta.websocket.OnError;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;

@Component
@ServerEndpoint("/chat/{id}")
public class ChatService {
//	static Set<Session> sessions = new HashSet<Session>();
	static HashMap<String, Session> userList = new HashMap<String, Session>();

	@OnOpen
	public void open(Session session, @PathParam("id") String id) {
		System.out.println("Open");
		userList.put(id, session);
		Set<String> keys = userList.keySet();
		JSONObject json = new JSONObject();

		System.out.println("접속자 수 : " + userList.size());
		json.put("msg", "현재 접속자 수 : " + userList.size() + "&#10;" + id + " 님이 입장하셨습니다.");

		for (String key : keys) {
			Session s = userList.get(key);
			s.getAsyncRemote().sendText(json.toString());
		}
		json.clear();
	}

	@OnClose
	public void close(Session session) {
		System.out.println("Close");
		System.out.println("접속자 수 : " + userList.size());
		Set<String> keys = userList.keySet();
		JSONObject json = new JSONObject();
		
		try {
			for (String key : keys) {
				if (session.getId().equals(userList.get(key).getId())) {
					json.put("msg", key + " 님이 나가셨습니다.");
					userList.remove(key, session);
					keys = userList.keySet();
					break;
				}
			}
			
			for (String key : keys) {
				Session s = userList.get(key);
				if(s.isOpen()) s.getAsyncRemote().sendText(json.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@OnError
	public void error(Throwable error) {
		System.out.println("Error");
	}

	@OnMessage
	public void message(String message, Session session) {
		try {
			Set<String> keys = userList.keySet();
			JSONObject json = new JSONObject(message);

			if (json.get("msg") != null) {
				
				for (String key : keys) {
					Session s = userList.get(key);
					if(s.isOpen()) s.getAsyncRemote().sendText(json.toString());
				}
				
				json.clear();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
