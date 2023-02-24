package xyz.hugme.hugmebackend.api.client.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import xyz.hugme.hugmebackend.domain.user.client.Client;
import xyz.hugme.hugmebackend.domain.user.counselor.Gender;

@Getter
@NoArgsConstructor
public class SaveClientDto {
    private Gender gender;
    private String name;
    private String email;
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








