package xyz.hugme.hugmebackend.api.auth.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import xyz.hugme.hugmebackend.api.auth.dto.LoginDto;
import xyz.hugme.hugmebackend.api.auth.service.AuthService;
import xyz.hugme.hugmebackend.api.common.SingleRspsTemplate;
import xyz.hugme.hugmebackend.api.common.UserStatus;
import xyz.hugme.hugmebackend.domain.user.Role;
import xyz.hugme.hugmebackend.global.auth.SessionStatus;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

// 로그인 인증 컨트롤러
@RequiredArgsConstructor
@RestController
public class AuthController {
    private final AuthService authService;

    // 상담사 로그인
    @PostMapping("/sign-in/counselors")
    public SingleRspsTemplate<Void> signInCounselor(@RequestBody @Valid LoginDto loginDto,HttpServletRequest request){
        String username = authService.signInCounselor(loginDto, request);

        UserStatus userStatus = new UserStatus(true, Role.COUNSELOR, username);// 로그인이 성공했으므로, 그것에 맞는 UserStatus 객체 반환
        return new SingleRspsTemplate<>(HttpStatus.OK.value(), userStatus);
    }

    // 내담자 로그인
    @PostMapping("/sign-in/clients")
    public SingleRspsTemplate<Void> signInClient(@RequestBody @Valid LoginDto loginDto, HttpServletRequest request){
        String username = authService.singInClient(loginDto, request);

        UserStatus userStatus = new UserStatus(true, Role.CLIENT, username);
        return new SingleRspsTemplate<>(HttpStatus.OK.value(), userStatus);
    }

    @PostMapping("/sign-out")
    public ResponseEntity<SingleRspsTemplate<Void>> signOut(HttpServletRequest request){
        authService.signOut(request);

        UserStatus userStatus = new UserStatus(false);
        SingleRspsTemplate<Void> rspsTemplate = new SingleRspsTemplate<>(HttpStatus.OK.value(), userStatus);

        return ResponseEntity.ok().header("Set-Cookie", "session_id=; expires=Thu, 01 Jan 1970 00:00:00 GMT; Secure; SameSite=None").
                body(rspsTemplate);
    }



    // 아래 메소드들은 테스트용.
    @GetMapping("/all-cookies")
    public String getAllcookies(HttpServletRequest request){
        StringBuilder sb = new StringBuilder();
        Cookie[] cookies = request.getCookies();

        if (cookies != null){
            for (Cookie cookie : cookies) {
                sb.append(cookie.getName() + " : " + cookie.getValue() + " || ");
            }
        }
        return sb.toString();
    }

    @PostMapping("/invalidatesession")
    public String invalidatesession(HttpServletRequest request){
        request.getSession().invalidate();
        return "inval";
    }

    @GetMapping("/new-cookie")
    public ResponseEntity<String> newCookie(@SessionStatus UserStatus userStatus, HttpServletResponse response){
        ResponseCookie cookie = ResponseCookie.from("name", "value")
                .httpOnly(true)
                .secure(true)
                .sameSite("None")
                .maxAge(86400) // seconds
                .build();

        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString()).body("path : " + cookie.getPath() + " domain : " + cookie.getDomain());
    }

    @GetMapping("/delete-cookie")
    public ResponseEntity<String> deleteCookie(HttpServletResponse response){
        ResponseCookie cookie = ResponseCookie.from("name", "value")
                .httpOnly(true)
                .secure(true)
                .sameSite("None")
                .maxAge(0) // seconds
                .build();

        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString()).body("path : " + cookie.getPath() + " domain : " + cookie.getDomain() + " maxage : " + cookie.getMaxAge());
    }



//    @GetMapping("/delete-cookie")
//    public ResponseEntity<String> deleteCookie(HttpServletResponse response){
//        ResponseCookie cookie = ResponseCookie.from("name", "value")
//                .httpOnly(true)
//                .secure(true)
//                .sameSite("None")
//                .maxAge(0) // seconds
//                .build();
//
//        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString()).body("path : " + cookie.getPath() + " domain : " + cookie.getDomain() + " maxage : " + cookie.getMaxAge());
//    }



    /** 아래 주석은 실 배포환경에서 CORS 문제가 발생할 시 활용해보자.*/
//    @GetMapping("/sign-in-test")
//    public String test(HttpServletRequest request){
//        StringBuilder sb = new StringBuilder();
//        HttpSession session = request.getSession();
//
//        String name = (String) session.getAttribute("name");
//        System.out.println("name = " + name);
//        sb.append("session: name " + name);
//        String email = (String) session.getAttribute("email");
//        System.out.println("email = " + email);
//        sb.append("session: email " + email);
//
//        return sb.toString();
//    }
//
//    @GetMapping("/auth-header-test")
//    public String authHeaderTest(HttpServletRequest request){
//        String header = request.getHeader(HttpHeaders.AUTHORIZATION);
//        System.out.println("header = " + header);
//
//        HttpSession session = request.getSession();
//
//        String name = (String) session.getAttribute("name");
//        System.out.println("name = " + name);
//        return header+ "그리고 세션name은" + name;
//    }

}










