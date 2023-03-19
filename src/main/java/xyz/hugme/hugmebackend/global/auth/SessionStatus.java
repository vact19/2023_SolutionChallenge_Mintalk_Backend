package xyz.hugme.hugmebackend.global.auth;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// 사용자의 로그인정보를 파악 && 쿠키 재발급하기 위한 ArgumentResolver

// Handler Method 에
/**@SessionStatus UserStatus userStatus 이렇게 사용*/
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface SessionStatus {
}
