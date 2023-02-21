package xyz.hugme.hugmebackend.api.counselor.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import xyz.hugme.hugmebackend.domain.user.counselor.Counselor;
import xyz.hugme.hugmebackend.domain.user.counselor.Field;
import xyz.hugme.hugmebackend.domain.user.counselor.Gender;

import java.util.List;
import java.util.Set;

@Getter
@NoArgsConstructor
public class CounselorSignUpDto {
    private String name;
    private String email;
    private String password;
    private Gender gender;
    private String shortIntroduction;
    private String introduction;
    private String contact;
    private String location;
    private List<String> careers;
    private Set<Field> fields;

    public Counselor toEntity(String encodedPassword){
        return Counselor.builder()
                .name(name)
                .email(email)
                .password(encodedPassword)
                .gender(gender)
                .shortIntroduction(shortIntroduction)
                .introduction(introduction)
                .contact(contact)
                .location(location)
                .careers(careers)
                .fields(fields)
                .build();
    }
}























