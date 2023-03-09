package xyz.hugme.hugmebackend.api.common;

import lombok.Getter;
import lombok.NoArgsConstructor;
import xyz.hugme.hugmebackend.domain.user.Role;

// 응답 템플릿
// 응답 DTO가 리스트가 아닐 때
@Getter
@NoArgsConstructor
public class SingleRspsTemplate<T> {
    private int statusCode;
    private T data;
    private Role role;
    private String username;

    public SingleRspsTemplate(int statusCode, T data) {
        this.statusCode = statusCode;
        this.data = data;
    }
}
