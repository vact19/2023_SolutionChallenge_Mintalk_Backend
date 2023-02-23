package xyz.hugme.hugmebackend.api.counselor.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.hugme.hugmebackend.api.common.RspsTemplate;
import xyz.hugme.hugmebackend.api.common.SingleRspsTemplate;
import xyz.hugme.hugmebackend.api.counselor.dto.CounselorInfoDto;
import xyz.hugme.hugmebackend.api.counselor.dto.CounselorListDto;
import xyz.hugme.hugmebackend.api.counselor.dto.CounselorSignUpDto;
import xyz.hugme.hugmebackend.api.counselor.service.ApiCounselorService;
import xyz.hugme.hugmebackend.domain.user.counselor.Counselor;

import java.net.URI;

@RequiredArgsConstructor
@RestController
public class CounselorController {

    private final ApiCounselorService apiCounselorService;

    // 테스트용 메소드. 나중에 조회기능 만들때 이거 바꾸면 될 듯
    @GetMapping
    public RspsTemplate<CounselorListDto> getList(){
        RspsTemplate<CounselorListDto> rspsTemplate = apiCounselorService.findAll();
        return rspsTemplate;
    }

    // 상담사 회원가입
    @PostMapping("/counselors")
    public ResponseEntity signIn(@RequestBody CounselorSignUpDto counselorSignUpDto){
        Counselor savedCounselor = apiCounselorService.signUp(counselorSignUpDto);
        return ResponseEntity.created(URI.create("/counselors/" + savedCounselor.getId())).build();
    }

    // 상담사 자기소개 페이지
    @GetMapping("/counselors/{id}")
    public SingleRspsTemplate<CounselorInfoDto> counselorInfo(@PathVariable Long id){
        // id로 리뷰, 상담사 찾고
        // 해당 정보들을 Dto에 넣어준다
        SingleRspsTemplate<CounselorInfoDto> rspsTemplate = apiCounselorService.findCounselorAndReviewsById(id);
        return rspsTemplate;
    }



}













