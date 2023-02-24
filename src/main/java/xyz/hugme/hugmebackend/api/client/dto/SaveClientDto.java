package xyz.hugme.hugmebackend.api.client.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import xyz.hugme.hugmebackend.domain.user.client.Client;
import xyz.hugme.hugmebackend.domain.user.counselor.Gender;

@Getter
@AllArgsConstructor
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








