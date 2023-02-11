package xyz.hugme.hugmebackend.domain.counselor;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;


@Getter
public enum Gender { // 상담사 성별
    MALE("남성"), FEMALE("여성");

    private final String desc;

    Gender(String desc) {
        this.desc = desc;
    }

    // 역직렬화를 위함. FieldConverter에서 사용.
    @JsonCreator
    public static Gender from(String gender){
        return Gender.valueOf(gender.toUpperCase());
    }
}
