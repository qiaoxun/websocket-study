package com.study.encoder;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

/**
 * Encode a chat message as JSON
 * For example, (new ChatMessageEncoder("Peter", "Duke", "How are you?"))
 * is encoder as follows:
 * {"type" : "chat", "target" : "Duke", "message" : "How are you?"}
 */
public class ChatMessageEncoder implements Encoder.Text<String> {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(EndpointConfig arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String encode(String arg0) throws EncodeException {
		// TODO Auto-generated method stub
		return null;
	}

}
