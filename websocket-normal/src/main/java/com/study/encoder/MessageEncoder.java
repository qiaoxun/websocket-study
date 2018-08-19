package com.study.encoder;

import com.alibaba.fastjson.JSONObject;
import com.study.message.ChatMessage;
import com.study.message.Message;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;
import java.util.Map;

/**
 * Encode a message as JSON
 * For example, (new MessageEncoder("Peter", "Duke", "How are you?"))
 * is encoder as follows:
 * {"type" : "chat", "target" : "Duke", "message" : "How are you?"}
 */
public class MessageEncoder implements Encoder.Text<Message> {

	/* Stores the name-pairs from a JSON message as a map */
	private Map<String, String> messageMap;

	@Override
	public void destroy() {

	}

	@Override
	public void init(EndpointConfig arg0) {

	}

	/**
	 * Create a new message if the message could be encode
	 * @param msg
	 * @return
	 * @throws EncodeException
	 */
	@Override
	public String encode(Message msg) throws EncodeException {
        // Access properties in chatMessage and write JSON text

		return JSONObject.toJSONString(msg);
	}



}
