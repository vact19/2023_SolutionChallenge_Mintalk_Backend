package xyz.hugme.hugmebackend.api.common;

import lombok.Getter;
import lombok.NoArgsConstructor;

// 응답 템플릿
// 응답 DTO가 리스트가 아닐 때
@Getter
@NoArgsConstructor
public class SingleRspsTemplate<T> {
    private int status;
    private T data;

    public SingleRspsTemplate(int status, T data) {
        this.status = status;
        this.data = data;
    }
}
