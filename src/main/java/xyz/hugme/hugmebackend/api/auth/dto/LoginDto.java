package xyz.hugme.hugmebackend.api.auth.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


public class LoginDto {

    @Getter
    @NoArgsConstructor
    public static class Request{
        private String email;
        private String password;

        @Builder
        public Request(String email, String password) {
            this.email = email;
            this.password = password;
        }
    }

    @Getter
    @NoArgsConstructor
    public static class Response{
        private String accessToken;
        private String refreshToken;

        @Builder
        public Response(String accessToken, String refreshToken){
            this.accessToken = accessToken;
            this.refreshToken = refreshToken;
        }
    }

}

