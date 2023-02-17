package xyz.hugme.hugmebackend.api.common;

import lombok.Getter;
import lombok.NoArgsConstructor;

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
