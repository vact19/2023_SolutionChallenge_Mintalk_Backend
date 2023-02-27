package xyz.hugme.hugmebackend.global.exception.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class ErrorResponseDto {
    private int statusCode;
    private HttpStatus httpStatus;
    private String errorMessage;
}
