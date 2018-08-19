package com.study.endpoint;

import com.study.bean.BotBean;
import com.study.decoder.MessageDecoder;
import com.study.encoder.MessageEncoder;
import com.study.message.ChatMessage;
import com.study.message.JoinMessage;
import com.study.message.Message;
import com.study.message.UserMessage;
import com.study.utils.UserOptions;
import org.apache.commons.lang3.StringUtils;

import javax.enterprise.concurrent.ManagedExecutorService;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

@ServerEndpoint(
		value = "/websocketbot",
		decoders = {MessageDecoder.class},
		encoders = {MessageEncoder.class}
		)
public class BotEndpoint {
	// a queue that contains all sessions
	private static Queue<Session> queue = new ConcurrentLinkedQueue<>();

	// a map that contains all users' name
	private static Map<String, String> idNameMap = new ConcurrentHashMap<>();

	// Bot functionality bean
	private BotBean botBean;

	// Executor Service for asynchronous processing
	private ManagedExecutorService mes;

	/**
	 *  on open connection
	 * @param session
	 */
	@OnOpen
	public void openConnection(Session session, EndpointConfig conf) {
		System.out.println(session.getId() + " openConnection");
		queue.add(session);
	}

	/**
	 * When message coming
	 * @param session
	 * @param message
	 */
	@OnMessage
	public void message(Session session, Message message) {
		System.out.println(message);
		System.out.println(session.getId() + " message " + message.getName());
		if (message instanceof ChatMessage) {
			String target = ((ChatMessage) message).getTarget();
			// send message to the target
			if (StringUtils.isNotBlank(target) && !StringUtils.equals("all", target)) {

				final StringBuilder id = new StringBuilder();
				idNameMap.forEach((k, v) -> {
					if(StringUtils.equals(v, target)) {
						id.append(k);
					}
				});
				sendOnlyOneById(id.toString(), message);
			} else {
				// send message to all users
				sendAll(message);
			}
		} else if (message instanceof JoinMessage) {
			String name = message.getName();
			UserMessage newGuyIsHere = new UserMessage(name, UserOptions.ADD.getOption());

			// get all users' name
			StringBuilder allUsersStr = new StringBuilder();
			idNameMap.forEach((k, v) -> allUsersStr.append(v + ","));
			idNameMap.forEach((k, v) -> System.out.println("key is " + k + " v is " + v));
			newGuyIsHere.setAllUserNames(allUsersStr.toString());
			System.out.println("setAllUserNames " + allUsersStr.toString());
			sendAll(newGuyIsHere);

			// put all users' name into this map
			idNameMap.put(session.getId(), name);
			System.out.println("idNameMap's size " + idNameMap.size());
		}
	}

	/**
	 * When error occurs
	 * @param session
	 * @param error
	 */
	@OnError
	public void error(Session session, Throwable error) {
		queue.remove(session);
		Message message = new UserMessage(idNameMap.get(session.getId()), UserOptions.DELETE.getOption());
		sendAll(message);
		error.printStackTrace();
	}

	/**
	 * When a connection closed
	 */
	@OnClose
	public void closeConnection(Session session, CloseReason closeReason) {
		System.out.println(session.getId() + " closeConnection " + closeReason.getReasonPhrase());
		queue.remove(session);
		Message message = new UserMessage(idNameMap.get(session.getId()), UserOptions.DELETE.getOption());
		sendAll(message);
	}

	/**
	 * send message to all users
	 * @param message
	 */
	private void sendAll(Message message) {
		for (Session s : queue) {
			try {
				s.getBasicRemote().sendObject(message);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * send message to all except me
	 * @param session
	 * @param message
	 */
	private void sendAllExceptMe(Session session, Message message) {
		for (Session s : queue) {
			if (s == session)
				continue;

			try {
				s.getBasicRemote().sendObject(message);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * only send message to me
	 * @param session
	 * @param message
	 */
	private void sendOnlyMe(Session session, Message message) {
		try {
			session.getBasicRemote().sendObject(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 *  send message to the target
	 * @param targetId
	 * @param message
	 */
	private void sendOnlyOneById(String targetId, Message message) {
		for (Session s : queue) {
			if (!StringUtils.equals(s.getId(), targetId))
				continue;

			try {
				s.getBasicRemote().sendObject(message);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
