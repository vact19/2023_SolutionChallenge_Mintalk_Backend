package xyz.hugme.hugmebackend.api.websocket.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MessageDto {
    private String type;
    private String sender;
    private String channelId;
    private Object data;

    public void setSender(String sender){
        this.sender = sender;
    }
    public void newConnect(){
        this.type = "new";
    }
    public void closeConnect(){
        this.type = "close";
    }
}