package xyz.hugme.hugmebackend.global.auth;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// 사용자의 로그인정보를 파악하기 위한 ArgumentResolver

// Handler Method 에
/**@LogInStatus LogInStatus logInStatus 이렇게 사용*/
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface LogInStatus {
}
