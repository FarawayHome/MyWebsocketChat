package edu.my.controller;

import com.alibaba.fastjson.JSON;
import edu.my.pojo.vo.ChatMessage;
import edu.my.pojo.vo.OnlineStudents;
import org.springframework.web.socket.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class SystemWebSocketHandler implements WebSocketHandler {

	private static ArrayList<WebSocketSession> users = new ArrayList<WebSocketSession>();
	private Map<String, String> userMaps = new HashMap<String, String>();

	// private ArrayList<HashMap> userMaps = new ArrayList<HashMap>();

	@Override
	public void afterConnectionEstablished(WebSocketSession session)
			throws Exception {
		System.out.println("ConnectionEstablished");
		String i = session.getId();
		users.add(session);
		/*每成功一个连接，将用户websocketsession存起来*/
		System.out.println(i + " user size:" + users.size());
	}

	@Override
	public void handleMessage(WebSocketSession session,
			WebSocketMessage<?> message) throws Exception {
		String schatMessage = (String) message.getPayload();
		System.out.println(schatMessage + "客户端传来的消息");
		ChatMessage chatMessage = JSON.parseObject(schatMessage, ChatMessage.class);
		String type = chatMessage.getType();
		if (type.equals("0")) {
			/*如果类型是0，说明是刚登陆，则装到用户map*/
			userMaps.put(session.getId(), chatMessage.getUsername());
			/*讲用户信息发送给所有人*/
			sendtoAllOnlineUsers();
		} else if (type.equals("1")) {
			/*如果是1，说明是普通消息，即发送给所有用户*/
			sendMessageToUsers(message);
		}

	}

	@Override
	public void handleTransportError(WebSocketSession session,
			Throwable exception) throws Exception {
		if (session.isOpen()) {
			session.close();
		}
		removeUser(session);
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session,
			CloseStatus closeStatus) throws Exception {
		System.out.println("ConnectionClosed");
		removeUser(session);
	}

	@Override
	public boolean supportsPartialMessages() {
		return false;
	}

	/**
	 * 给所有在线用户发送消息
	 * 
	 * @param message
	 */
	private void sendMessageToUsers(WebSocketMessage<?> message) {
		for (WebSocketSession user : users) {
			try {
				if (user.isOpen()) {
					user.sendMessage(message);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	//	给所有在线用户发送送新上线的用户信息
	private void sendtoAllOnlineUsers() {
		OnlineStudents onlines = new OnlineStudents();
		ArrayList<String> username = new ArrayList<String>();
		for (Entry<String, String> u : userMaps.entrySet()) {
			username.add(u.getValue());
		}
		onlines.setType("3");
		onlines.setUsernames(username);
		String text = JSON.toJSONString(onlines);

		System.out.println(text);
		text = text.replace("\"3\"", "3");
		System.out.println(text);
		for (WebSocketSession user : users) {
			try {
				if (user.isOpen()) {
					user.sendMessage(new TextMessage(text));
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/* 发送当时所有的在线用户*/

	private void sendOnlineUsers(WebSocketSession session) {
		OnlineStudents onlines = new OnlineStudents();
		ArrayList<String> username = new ArrayList<String>();
		for (Entry<String, String> u : userMaps.entrySet()) {
			username.add(u.getValue());
		}
		onlines.setType("3");
		onlines.setUsernames(username);
		String text = JSON.toJSONString(onlines);

		System.out.println(text);
		try {
			session.sendMessage(new TextMessage(text));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 断开连接时移除用户
	 * 
	 * @param session
	 */
	private void removeUser(WebSocketSession session) {
		users.remove(session);
		userMaps.remove(session.getId());
		sendtoAllOnlineUsers();
	}
}
