package xyz.hugme.hugmebackend.global.auth;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


// 현재 로그인중인 상담사 객체를 편리하게 조회하기 위한 ArgumentResolver
// Handler Method 에
/** @SessionCounselor Counselor counselor */
// 이렇게 사용
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface SessionCounselor {
}
