package com.study.message;

public class ChatMessage implements Message {
	private final String type = "chat";
	private String name;
	private String target;
	private String message;

	public ChatMessage() {
	}

	public ChatMessage(String name, String target, String message) {
		this.name = name;
		this.target = target;
		this.message = message;
	}

	public String getType() {
		return type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
