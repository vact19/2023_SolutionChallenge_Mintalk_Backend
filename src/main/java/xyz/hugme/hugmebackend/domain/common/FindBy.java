package xyz.hugme.hugmebackend.domain.common;

import lombok.Getter;

// 예외처리를 위한 enum class
// 사용처: CounselorService. ClientService 등...
@Getter
public enum FindBy {
    EMAIL, ID;
}
