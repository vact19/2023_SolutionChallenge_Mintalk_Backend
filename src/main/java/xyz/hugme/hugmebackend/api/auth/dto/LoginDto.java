package xyz.hugme.hugmebackend.api.auth.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;

// 상담사, 내담자 공용 로그인 요청 DTO
@Getter
@NoArgsConstructor
public class LoginDto {

    @Email(message = "Email 형식어야 합니다.")
    private String email;
    @Length(min = 4, message = "4자 이상이어야 합니다.")
    private String password;
}

