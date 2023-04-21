package xyz.hugme.hugmebackend.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
public class ChatMessage {
    private MessageType type;
    private String content;
    private String sender;
    private String time; // 앞단에서 moment.js 사용을 위해 String type으로 생성

    @Builder
    public ChatMessage(MessageType type, String content, String sender, String time) {
        this.type = type;
        this.content = content;
        this.sender = sender;
        this.time = time;
    }
}
