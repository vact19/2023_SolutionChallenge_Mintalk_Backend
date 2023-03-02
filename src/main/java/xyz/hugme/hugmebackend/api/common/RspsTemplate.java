package xyz.hugme.hugmebackend.api.common;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
// 응답 템플릿
// 응답 DTO를 리스트로 보낼 때
@Getter
@NoArgsConstructor
public class RspsTemplate<T> {
    private int statusCode;
    private List<T> data;

    public RspsTemplate(int statusCode, List<T> data) {
        this.statusCode = statusCode;
        this.data = data;
    }
}
