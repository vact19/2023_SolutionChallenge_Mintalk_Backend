package xyz.hugme.hugmebackend.global.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {

    // 안아줘요
    ANAJOURYO(200, "좋아해요")

    // Client
    , CLIENT_ID_NOT_FOUND(400, "해당 id로 내담자를 찾을 수 없습니다.")
    , CLIENT_EMAIL_NOT_FOUND(400, "해당 email로 내담자를 찾을 수 없습니다.")
    // Counselor
    , COUNSELOR_ID_NOT_FOUND(400, "해당 id로 상담사를 찾을 수 없습니다.")
    , COUNSELOR_EMAIL_NOT_FOUND(400, "해당 email로 상담사를 찾을 수 없습니다.")
    , COUNSELOR_NOT_AUTHENTICATED(401, "상담사 인증이 필요합니다.")

    // 로그인, 인증
    , NOT_MATCHING_PASSWORD(400, "비밀번호가 일치하지 않습니다.")
    , NOT_AUTHENTICATED_CLIENT(401, "내담자 인증이 필요합니다.")

    ;




    private int status;
    private String message;

    ErrorCode(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
