package org.geeks.agileporker.app.sample;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class GreetingController {


    @MessageMapping("/hello")
    public void greeting(HelloMessage message) throws Exception {
    	sendGreeting(message.getName());
    }
    
    @SendTo("/topic/greetings")
    public Greeting sendGreeting(String message) throws Exception {
        return new Greeting("Hello, " + message + "!");
    }
    
    @MessageMapping("/hi/{name}")
    @SendTo("/topic/{name}/greetings")
    public Greeting handleBaz(@DestinationVariable String name) {
    	return new Greeting("Hi, " + name + "!");
    }

}
