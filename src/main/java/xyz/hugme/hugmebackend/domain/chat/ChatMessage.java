package xyz.hugme.hugmebackend.domain.chat;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import xyz.hugme.hugmebackend.domain.common.BaseTimeEntity;
import xyz.hugme.hugmebackend.domain.user.Role;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class ChatMessage extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Role senderRole; // sender 가 어떤 타입일지 특정할 수 없으므로, senderRole 변수를 통해 파악함
    private Long senderId;

    @Enumerated(EnumType.STRING)
    private Role receiverRole;
    private Long receiverId; // receiver 가 어떤 타입일지 특정할 수 없으므로, receiverRole 변수를 통해 파악함

    private String content;

    @Builder
    public ChatMessage(Role senderRole, Long senderId, Role receiverRole, Long receiverId, String content) {
        this.senderRole = senderRole;
        this.senderId = senderId;
        this.receiverRole = receiverRole;
        this.receiverId = receiverId;
        this.content = content;
    }
}



