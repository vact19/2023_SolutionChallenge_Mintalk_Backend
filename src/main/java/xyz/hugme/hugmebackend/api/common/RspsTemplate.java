package xyz.hugme.hugmebackend.api.common;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
// 응답 템플릿
// 응답 DTO를 리스트로 보낼 때
@Getter
@NoArgsConstructor
public class RspsTemplate<T> {
    private int status;
    private List<T> data;

    public RspsTemplate(int status, List<T> data) {
        this.status = status;
        this.data = data;
    }
}
