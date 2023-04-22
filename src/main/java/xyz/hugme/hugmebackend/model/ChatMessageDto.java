package xyz.hugme.hugmebackend.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
public class ChatMessageDto {
    private MessageType type;
    private String content;
    private String sender;
    private String time; // 앞단에서 moment.js로 생성해서 보내게 될 것. moment().calendar()


//    private Role senderRole; // sender 가 어떤 타입일지 특정할 수 없으므로, senderRole 변수를 통해 파악함
//    private Long senderId;
//
//    private Role receiverRole;
//    private Long receiverId; // receiver 가 어떤 타입일지 특정할 수 없으므로, receiverRole 변수를 통해 파악함
//
//    private String content;

    @Builder
    public ChatMessageDto(MessageType type, String content, String sender, String time) {
        this.type = type;
        this.content = content;
        this.sender = sender;
        this.time = time;
    }
}
