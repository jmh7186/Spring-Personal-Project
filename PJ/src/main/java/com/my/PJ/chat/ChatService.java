package com.my.PJ.chat;

import java.util.HashMap;
import java.util.Set;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
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
	HashMap<String, Object> msgHM = new HashMap<String, Object>();

	@OnOpen
	public void open(Session session, @PathParam("id") String id) {
		System.out.println("Open");
		userList.put(id, session);
		Set<String> keys = userList.keySet();

		System.out.println("접속자 수 : " + userList.size());
		msgHM.put("msg", "현재 접속자 수 : " + userList.size() + "&#10;" + id + " 님이 입장하셨습니다.");

		JSONObject json = new JSONObject(msgHM);
		for (String key : keys) {
			Session s = userList.get(key);
			s.getAsyncRemote().sendText(json.toJSONString());
		}
		json.clear();
		msgHM.clear();
	}

	@OnClose
	public void close(Session session) {
		System.out.println("Close");
		System.out.println("접속자 수 : " + userList.size());
		Set<String> keys = userList.keySet();
		
		try {
			for (String key : keys) {
				if (session.getId().equals(userList.get(key).getId())) {
					msgHM.put("msg", key + " 님이 나가셨습니다.");
					JSONObject json = new JSONObject(msgHM);
					
					for (String _key : keys) {
						Session s = userList.get(_key);
						if(s.isOpen()) s.getAsyncRemote().sendText(json.toJSONString());
					}
					
					userList.remove(key, session);
				}
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
			JSONParser parse = new JSONParser();
			JSONObject json = (JSONObject) parse.parse(message);
			Set<String> keys = userList.keySet();

			if (json.get("msg") != null) {
				msgHM.put("id", json.get("id"));
				msgHM.put("msg", json.get("msg"));
				json = new JSONObject(msgHM);
				
				for (String key : keys) {
					Session s = userList.get(key);
					if(s.isOpen()) s.getAsyncRemote().sendText(json.toJSONString());
				}
				
				json.clear();
				msgHM.clear();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
