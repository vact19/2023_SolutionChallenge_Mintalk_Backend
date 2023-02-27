package xyz.hugme.hugmebackend.global.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {

    // 안아줘요
    ANAJOURYO(200, "좋아해요");




    private int status;
    private String message;

    ErrorCode(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
