package xyz.hugme.hugmebackend.api.auth.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

// 상담사, 내담자 공용 로그인 요청 DTO
@Getter
@NoArgsConstructor
public class LoginDto {
    private String email;
    private String password;
}

