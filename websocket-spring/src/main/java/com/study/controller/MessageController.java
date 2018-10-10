package com.study.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.study.message.Message;

@Controller
public class MessageController {
	private static SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
	
	@MessageMapping("/chat")
	@SendTo("/topic/messages")
	public Message send(Message message) {
		String time = sdf.format(new Date());
		return new Message(message.getForm(), message.getText(), time);
	}
	
}
