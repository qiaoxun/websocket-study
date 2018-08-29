package com.study.message;

public class Message {
	private String form;
	private String text;
	private String time;
	
	public Message(String form, String text, String time) {
		super();
		this.form = form;
		this.text = text;
		this.time = time;
	}
	public String getForm() {
		return form;
	}
	public void setForm(String form) {
		this.form = form;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	
}
