package xyz.hugme.hugmebackend.api.client.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import xyz.hugme.hugmebackend.domain.user.client.Client;
import xyz.hugme.hugmebackend.domain.user.counselor.Gender;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

// 사용자 회원가입 DTO
@Getter
@NoArgsConstructor
public class SaveClientDto {
    private String name;
    private Gender gender;

    @Email(message = "Email 형식이어야 합니다")
    @NotBlank(message = "Email은 비어있을 수 없습니다")
    private String email;
    @Length(min = 4, max = 20, message = "비밀번호는 4~20자 사이어야 합니다")
    private String password;

    public Client toEntity(String encodedPassword){
        return Client.builder()
                .gender(gender)
                .name(name)
                .email(email)
                .password(encodedPassword)
                .build();
    }
}








