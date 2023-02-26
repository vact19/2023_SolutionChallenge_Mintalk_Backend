package xyz.hugme.hugmebackend.api.auth.dto;

import lombok.Builder;
import lombok.Getter;

// 상담사, 내담자 공용 로그인 요청 DTO
@Getter
public class LoginDto {
    private String email;
    private String password;

    @Builder
    public LoginDto(String email, String password) {
        this.email = email;
        this.password = password;
    }
}

