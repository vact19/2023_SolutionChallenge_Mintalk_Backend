package xyz.hugme.hugmebackend.api.common;

import lombok.Getter;

// 응답 템플릿
// 응답 DTO가 리스트가 아닐 때
@Getter
public class SingleRspsTemplate<T> {
    private int statusCode;
    private T data;
    private UserStatus userStatus;


    // 응답할 data가 없을 때, 로그인 정보만 받기
    public SingleRspsTemplate(int statusCode, UserStatus userStatus) {
        this.statusCode = statusCode;
        this.userStatus = userStatus;
    }

    public SingleRspsTemplate(int statusCode, T data, UserStatus userStatus) {
        this.statusCode = statusCode;
        this.data = data;
        this.userStatus = userStatus;
    }

    public SingleRspsTemplate(int statusCode, T data) {
        this.statusCode = statusCode;
        this.data = data;
    }
}
