package xyz.hugme.hugmebackend.api.auth.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import xyz.hugme.hugmebackend.api.auth.dto.LoginDto;
import xyz.hugme.hugmebackend.api.counselor.service.ApiCounselorService;
import xyz.hugme.hugmebackend.domain.user.counselor.Counselor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@RestController
public class AuthController {

    private final ApiCounselorService apiCounselorService;
    // 상담사 로그인
    @PostMapping("/sign-in/counselor")
    public String signIn(@RequestBody LoginDto.Request requestDto, HttpServletRequest request
                                      ){
        // username, password 검사, 토큰 생성 후 반환
        Counselor validatedCounselor = apiCounselorService.signIn(requestDto);

        // JsessionId 반환
        HttpSession session = request.getSession();
        session.setAttribute("name", validatedCounselor.getName());
        session.setAttribute("email", validatedCounselor.getEmail());

        return "success";
    }

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










