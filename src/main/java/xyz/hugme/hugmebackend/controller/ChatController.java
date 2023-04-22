package xyz.hugme.hugmebackend.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import xyz.hugme.hugmebackend.model.ChatMessage;

@Controller
public class ChatController  {

    // app/chat.send 
    // topic/public
    /**
     *  dest 로 publish 를 하면,
     *  broker 가 subs 쪽으로 메시지를 보내준다.
     **/

    /**
     *   registry.setApplicationDestinationPrefixes("/app");
     *   
     *   스프링 앱은
     *    "/app/chat.send" 에서
     *    prefix "/app"이 제거된 나머지 부분
     *    즉 "/chat.send" 를 읽어서 요청을 해당 핸들러 메서드에 매핑함
     */
    
    @MessageMapping("/chat.send")
    // return value 를 특정 destination 에 보낸다.
    // registry.enableSimpleBroker("/topic"); // 브로커에게 subs 경로를 알려줌
    @SendTo("/topic/public")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage){
        return chatMessage;
    }

    @MessageMapping("/chat.newUser")
    @SendTo("/topic/public")
    public ChatMessage newUser(@Payload ChatMessage chatMessage,
                               SimpMessageHeaderAccessor headerAccessor){
            // add user to websocket session
            headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
            return chatMessage;
    }
}
