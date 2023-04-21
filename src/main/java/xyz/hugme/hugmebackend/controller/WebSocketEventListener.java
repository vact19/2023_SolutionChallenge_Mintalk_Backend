package xyz.hugme.hugmebackend.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;
import xyz.hugme.hugmebackend.model.ChatMessage;
import xyz.hugme.hugmebackend.model.MessageType;

@Slf4j
@Component
public class WebSocketEventListener {

    @Autowired private SimpMessageSendingOperations sendingOperations;

    @EventListener
    public void handleWebsocketConnectListener(SessionConnectedEvent event){
        log.info("new connection!");
    }

    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event){
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        String username = (String) headerAccessor.getSessionAttributes().get("username");
        ChatMessage chatMessage = ChatMessage.builder()
                .type(MessageType.DISCONNECT)
                .sender(username)
                .build();

        sendingOperations.convertAndSend("/topic/public", chatMessage);
    }


}




















