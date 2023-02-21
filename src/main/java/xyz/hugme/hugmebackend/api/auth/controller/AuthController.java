package xyz.hugme.hugmebackend.api.auth.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import xyz.hugme.hugmebackend.api.auth.dto.LoginDto;
import xyz.hugme.hugmebackend.api.counselor.service.ApiCounselorService;
import xyz.hugme.hugmebackend.domain.user.counselor.Counselor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@RestController
public class AuthController {

    private final ApiCounselorService apiCounselorService;
    // 상담사 로그인
    @PostMapping("/sign-in/counselor")
    public String signIn(@RequestBody LoginDto.Request requestDto, HttpServletRequest request
                                        , HttpServletResponse response){
        // username, password 검사, 토큰 생성 후 반환
        Counselor validatedCounselor = apiCounselorService.signIn(requestDto);

        // JsessionId 반환
        HttpSession session = request.getSession();
        session.setAttribute("name", validatedCounselor.getName());
        session.setAttribute("email", validatedCounselor.getEmail());
        Cookie cookie = new Cookie("cookie's email", validatedCounselor.getEmail());
        cookie.setPath("/");
        response.addCookie(cookie);
        return "ok";
    }

    @GetMapping("/sign-in-test")
    public String test(HttpServletRequest request){
        StringBuilder sb = new StringBuilder();
        String name = (String) request.getSession().getAttribute("name");
        sb.append("session: name " + name);
        String email = (String) request.getSession().getAttribute("email");
        sb.append("session: email " + email);
        Cookie[] cookies = request.getCookies();
        sb.append("수동등록 쿠키값 출력 시작 : ");
        for (Cookie cookie : cookies) {
            sb.append("쿠키 이름 : " + cookie.getName());
            sb.append("쿠키 값 : " + cookie.getValue());
        }

        return sb.toString();
    }


}










