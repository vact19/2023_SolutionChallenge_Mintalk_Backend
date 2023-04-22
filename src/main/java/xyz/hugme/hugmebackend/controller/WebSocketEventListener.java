package xyz.hugme.hugmebackend.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;
import xyz.hugme.hugmebackend.model.ChatMessageDto;
import xyz.hugme.hugmebackend.model.MessageType;

/**
 *  connect / disconnect 이벤트 발생 시 처리 로직.
 *  컨트롤러에서는 메시지 전송 시의 핸들러 메서드를,
 *  해당 EventListener 클래스에서는 conn / disconn 이벤트 발생 시의 로직을 담는다.
 */
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
        ChatMessageDto chatMessageDto = ChatMessageDto.builder()
                .type(MessageType.DISCONNECT)
                .sender(username)
                .build();
        // broker 는 특정 subscription 경로로 메시지를 보낸다.
        sendingOperations.convertAndSend("/queue/user/1/chat/1", chatMessageDto);
    }


}




















