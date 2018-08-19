package com.study.bean;

import javax.inject.Named;

/**
 * The CDI bean (BotBean) is a Java class that contains the respond method.
 * This method compares the incoming chat message with a set of predefined questions and returns a chat response.
 */
@Named
public class BotBean {
	public String response(String msg) {
		
		return msg;
	}
}
