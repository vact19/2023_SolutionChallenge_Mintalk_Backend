package xyz.hugme.hugmebackend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import xyz.hugme.hugmebackend.domain.chat.ChatMessage;
import xyz.hugme.hugmebackend.domain.chat.ChatMessageRepository;
import xyz.hugme.hugmebackend.domain.user.Role;
import xyz.hugme.hugmebackend.model.ChatMessageDto;

@RequiredArgsConstructor
@Controller
public class ChatController  {

    private final ChatMessageRepository chatMessageRepository;

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

//    @SendTo("/topic/public")
    @SendTo("/queue/user/1/chat/1")
    public ChatMessageDto sendMessage(@Payload ChatMessageDto chatMessageDto){
        chatMessageRepository.findAll()
                .forEach(msg -> System.err.println(msg.getContent()));

        ChatMessage chatMessage = ChatMessage.builder()
                .senderId(1L)
                .senderRole(Role.CLIENT)
                .receiverId(1L)
                .receiverRole(Role.COUNSELOR)
                .content(chatMessageDto.getContent() + ", " + chatMessageDto.getTime())
                .build();

        chatMessageRepository.save(chatMessage);
        return chatMessageDto;
    }

    @MessageMapping("/chat.newUser")
//    @SendTo("/topic/public")
    @SendTo("/queue/user/1/chat/1")
    public ChatMessageDto newUser(@Payload ChatMessageDto chatMessageDto,
                                  SimpMessageHeaderAccessor headerAccessor){
            // add user to websocket session
            headerAccessor.getSessionAttributes().put("username", chatMessageDto.getSender());
            return chatMessageDto;
    }
}
