// Message.java
package com.example.demo.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chat")
@CrossOrigin(origins = "*")
public class ChatController {

    private final MessageService messageService;

    @Autowired
    public ChatController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping("/{roomId}/send")
    public ResponseEntity<Message> sendMessage(
            @PathVariable String roomId,
            @RequestBody Message message) {

        if (message.getSender() == null || message.getMessageType() == null) {
            return ResponseEntity.badRequest().build();
        }

        messageService.saveMessage(roomId, message);
        return ResponseEntity.ok(message);
    }

    @GetMapping("/{roomId}/messages")
    public ResponseEntity<List<Message>> getMessages(@PathVariable String roomId) {
        return ResponseEntity.ok(messageService.getMessages(roomId));
    }

    @DeleteMapping("/{roomId}")
    public ResponseEntity<Void> clearRoom(@PathVariable String roomId) {
        messageService.clearRoom(roomId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/rooms")
    public ResponseEntity<List<String>> getActiveRooms() {
        return ResponseEntity.ok(messageService.getActiveRooms());
    }
}
