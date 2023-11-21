package com.my.PJ.chat;

import java.text.SimpleDateFormat;
import java.util.Date;
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
	SimpleDateFormat sdf = new SimpleDateFormat("hh:mm");
//	static Set<Session> sessions = new HashSet<Session>();
	static HashMap<String, Session> userList = new HashMap<String, Session>();

	@OnOpen
	public void open(Session session, @PathParam("id") String id) {
		System.out.println("Open");
		userList.put(id, session);
		Set<String> keys = userList.keySet();
		JSONObject json = new JSONObject();

		System.out.println("ユーザー数 : " + userList.size() + "名");
		json.put("msg", "現在ユーザー数 : " + userList.size() + "名" + "&#10;" + id + " 様がご入場しました。");

		for (String key : keys) {
			Session s = userList.get(key);
			s.getAsyncRemote().sendText(json.toString());
		}
		json.clear();
	}

	@OnClose
	public void close(Session session) {
		System.out.println("Close");
		System.out.println("ユーザー数 : " + userList.size() + "名");
		Set<String> keys = userList.keySet();
		JSONObject json = new JSONObject();
		
		try {
			for (String key : keys) {
				if (session.getId().equals(userList.get(key).getId())) {
					json.put("msg", key + " 様がご退場しました。");
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
				Date now = new Date();
				json.put("time", sdf.format(now));
				for (String key : keys) {
					Session s = userList.get(key);
					if(s.isOpen()) {
						s.getAsyncRemote().sendText(json.toString());
					}
					System.out.println(json.toString());
				}
				json.clear();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
