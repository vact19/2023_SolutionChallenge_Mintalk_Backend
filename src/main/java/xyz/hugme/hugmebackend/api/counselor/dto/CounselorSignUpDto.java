package xyz.hugme.hugmebackend.api.counselor.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import xyz.hugme.hugmebackend.domain.user.counselor.Counselor;
import xyz.hugme.hugmebackend.domain.user.counselor.Gender;

@Getter
@NoArgsConstructor
public class CounselorSignUpDto {
    private String name;
    private Gender gender;
    private String email;
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























