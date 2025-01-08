package com.example.demo.Controller;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class MessageService {
    private final ConcurrentHashMap<String, List<Message>> chatRooms = new ConcurrentHashMap<>();

    public void saveMessage(String roomId, Message message) {
        chatRooms.computeIfAbsent(roomId, k -> new ArrayList<>()).add(message);
    }

    public List<Message> getMessages(String roomId) {
        return chatRooms.getOrDefault(roomId, new ArrayList<>());
    }

    public void clearRoom(String roomId) {
        chatRooms.remove(roomId);
    }

    public List<String> getActiveRooms() {
        return new ArrayList<>(chatRooms.keySet());
    }
}
