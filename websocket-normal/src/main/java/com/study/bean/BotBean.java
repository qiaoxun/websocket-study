package com.study.bean;

import javax.inject.Named;

@Named
public class BotBean {
	public String response(String msg) {
		
		return msg;
	}
}
