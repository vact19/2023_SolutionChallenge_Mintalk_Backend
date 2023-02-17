package xyz.hugme.hugmebackend.api.common;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

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
