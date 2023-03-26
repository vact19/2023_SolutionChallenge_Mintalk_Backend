package xyz.hugme.hugmebackend.api.websocket;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import xyz.hugme.hugmebackend.api.websocket.dto.MessageDto;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class WebSocketHandler extends TextWebSocketHandler {
    private final Map<String, WebSocketSession> sessions = new ConcurrentHashMap<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        String sessionId = session.getId();
        sessions.put(sessionId, session); // 세션 저장
        MessageDto messageDto = MessageDto.builder()
                .sender(sessionId)
//                .receiver("all")
                .data("HELLO")
                .build();

        messageDto.newConnect();

        sessions.values().forEach(s -> {
            try {
                s.sendMessage(new TextMessage(
                        new ObjectMapper().writeValueAsString(messageDto)));
            } catch (Exception e){
                e.printStackTrace();
            }
        });
    }

    // 메시지 받았을 때의 Handler
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        ObjectMapper objMapper = new ObjectMapper();
        MessageDto messageDto = objMapper.readValue(message.getPayload(), MessageDto.class);
        messageDto.setSender(session.getId()); // 이거 꼭 필요한가?

//        WebSocketSession receiverSession = sessions.get(messageDto.getReceiver());// Map에서 Receiver ID로 타겟 상대방 찾기
//        if (receiverSession != null && receiverSession.isOpen())
//            receiverSession.sendMessage(new TextMessage(objMapper.writeValueAsString(messageDto)));
    }

    // socket 연결 종료
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        String sessionId = session.getId();
        sessions.remove(sessionId);

        ObjectMapper objMapper = new ObjectMapper();
        MessageDto messageDto = new MessageDto();
        messageDto.closeConnect();
        messageDto.setSender(sessionId);

        sessions.values().forEach(s -> {
            try {
                s.sendMessage(new TextMessage(objMapper.writeValueAsString(messageDto)));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {

    }


}
