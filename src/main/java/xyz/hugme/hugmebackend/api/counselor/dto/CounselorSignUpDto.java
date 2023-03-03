package xyz.hugme.hugmebackend.api.counselor.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import xyz.hugme.hugmebackend.domain.user.counselor.Counselor;
import xyz.hugme.hugmebackend.domain.user.counselor.Gender;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

// 상담사 회원가입 DTO
@Getter
@NoArgsConstructor
public class CounselorSignUpDto {
    private String name;
    private Gender gender;
    @Email(message = "Email 형식이어야 합니다")
    @NotBlank(message = "Email은 비어있을 수 없습니다")
    private String email;
    @Length(min = 4, max = 20, message = "비밀번호는 4~20자 사이어야 합니다")
    private String password;


    public Counselor toEntity(String encodedPassword){
        return Counselor.builder()
                .name(name)
                .email(email)
                .password(encodedPassword)
                .gender(gender)
                .shortIntroduction("한줄 자기소개를 작성해 주세요")
                .introduction("나를 소개하는 글을 써 주세요.")
                .contact("연락처를 '-' 없이 적어 주세요.")
                .location("근무지를 입력해 주세요.")
                .careers(null) // 회원가입 시 경력, 특기분야를 기입하지 않는다.
                .fields(null)
                .build();
    }
}























