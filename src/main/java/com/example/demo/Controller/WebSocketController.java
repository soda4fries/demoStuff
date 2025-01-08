package com.example.demo.Controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.messaging.simp.SimpMessagingTemplate;

@Controller
public class WebSocketController {

    private final SimpMessagingTemplate messagingTemplate;

    public WebSocketController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    // Greet the user
    @MessageMapping("/hello") // Maps to "/app/hello"
    @SendTo("/topic/greetings") // Sends response to "/topic/greetings"
    public String greet(String name) {
        return "Hi, " + name + "!";
    }

    // Asynchronous updates
    @Scheduled(fixedRate = 5000) // Every 5 seconds
    public void sendAsyncUpdate() {
        messagingTemplate.convertAndSend("/topic/updates", "This is an async update: " + System.currentTimeMillis());
    }
}
