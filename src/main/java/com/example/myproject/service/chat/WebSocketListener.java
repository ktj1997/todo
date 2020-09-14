package com.example.myproject.service.chat;

import com.example.myproject.model.entity.chat.ChatMessage;
import com.example.myproject.model.entity.chat.MessageType;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
@RequiredArgsConstructor
public class WebSocketListener {

    private final Logger logger = LoggerFactory.getLogger(WebSocketListener.class);
    private final SimpMessageSendingOperations messagingTemplate;


    @EventListener
    public void handleWebSocketConnectionListener(SessionConnectEvent event) {
        logger.info("Received a new web socket connection");

        //Connect 되었을 때 logging하기
    }

    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        String userName = (String) headerAccessor.getSessionAttributes().get("username");

        logger.info("User Disconnected : " + userName);
        if (userName != null) {
            ChatMessage chatMessage = new ChatMessage();
            chatMessage.setType(MessageType.LEAVE);
            chatMessage.setSender(userName);

            messagingTemplate.convertAndSend("/topic/public", chatMessage);
        }
        //Disconnect 되었을 때 logging허가
    }
}
