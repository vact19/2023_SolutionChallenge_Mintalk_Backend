package xyz.hugme.hugmebackend.global.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {

    // 안아줘요
    ANAJOURYO(200, "좋아해요")

    // Client
    , CLIENT_ID_NOT_FOUND(404, "해당 ID로 내담자를 찾을 수 없습니다.")
    , CLIENT_EMAIL_NOT_FOUND(404, "해당 email로 내담자를 찾을 수 없습니다.")
    , CLIENT_EMAIL_ALREADY_EXISTS(409, "이미 존재하는 내담자 이메일입니다.")
    // Counselor
    , COUNSELOR_ID_NOT_FOUND(404, "해당 ID로 상담사를 찾을 수 없습니다.")
    , COUNSELOR_EMAIL_NOT_FOUND(404, "해당 email로 상담사를 찾을 수 없습니다.")
    , COUNSELOR_EMAIL_ALREADY_EXISTS(409, "이미 존재하는 상담사 이메일입니다.")

    // 상담사 리뷰
    , REVIEW_NOT_FOUND(404, "해당 ID로 리뷰를 찾을 수 없습니다.")

    // 로그인, 인증
    , PASSWORD_NOT_MATCHING(400, "비밀번호가 일치하지 않습니다.")
    , COUNSELOR_NOT_AUTHENTICATED(401, "상담사 인증이 필요합니다.")
    , CLIENT_NOT_AUTHENTICATED(401, "내담자 인증이 필요합니다.")
    , CLIENT_NOT_AUTHORIZED(403, "리뷰 변경 권한이 없습니다.")
    , SESSION_ID_ALREADY_EXISTS(409, "세션 ID가 중복됩니다. 다시 시도해 주세요.")
    , SESSION_NOT_FOUND(404, "세션을 찾을 수 없습니다.")


    ;




    private int status;
    private String message;

    ErrorCode(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
