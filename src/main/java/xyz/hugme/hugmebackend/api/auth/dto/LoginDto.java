package xyz.hugme.hugmebackend.api.auth.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

// 상담사, 내담자 공용 로그인 요청 DTO
@Getter
@NoArgsConstructor
public class LoginDto {

    @Email(message = "Email 형식이어야 합니다")
    @NotBlank(message = "Email은 비어있을 수 없습니다")
    private String email;
    @Length(min = 4, max = 20, message = "비밀번호는 4~20자 사이어야 합니다")
    private String password;
}

