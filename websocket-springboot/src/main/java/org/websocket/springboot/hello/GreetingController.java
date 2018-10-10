package org.websocket.springboot.hello;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

@Controller
public class GreetingController {
	
	@MessageMapping("/hello")
	@SendTo("/topic/greeting")
	public Greeting greeting(HelloMessage message) throws Exception {
		System.out.println(message.getName());
		return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
	}
	
}
