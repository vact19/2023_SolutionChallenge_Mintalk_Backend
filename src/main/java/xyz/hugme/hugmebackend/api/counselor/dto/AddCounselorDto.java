package xyz.hugme.hugmebackend.api.counselor.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import xyz.hugme.hugmebackend.domain.counselor.Counselor;
import xyz.hugme.hugmebackend.domain.counselor.Field;
import xyz.hugme.hugmebackend.domain.counselor.Gender;

import java.util.List;
import java.util.Set;

@Getter
@NoArgsConstructor
public class AddCounselorDto {
    private String name;
    private Gender gender;
    private String shortIntroduction;
    private String introduction;
    private String contact;
    private String email;
    private String location;
    private List<String> careers;
    private Set<Field> fields;

    public Counselor toEntity(){
        return Counselor.builder()
                .name(name)
                .gender(gender)
                .shortIntroduction(shortIntroduction)
                .introduction(introduction)
                .contact(contact)
                .email(email)
                .location(location)
                .careers(careers)
                .fields(fields)
                .build();
    }
}























