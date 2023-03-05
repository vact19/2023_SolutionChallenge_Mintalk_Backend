package xyz.hugme.hugmebackend.api.counselor.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.hugme.hugmebackend.api.common.RspsTemplate;
import xyz.hugme.hugmebackend.api.common.SingleRspsTemplate;
import xyz.hugme.hugmebackend.api.counselor.dto.*;
import xyz.hugme.hugmebackend.api.counselor.service.ApiCounselorService;
import xyz.hugme.hugmebackend.domain.user.counselor.Counselor;
import xyz.hugme.hugmebackend.global.auth.SessionCounselor;

import javax.validation.Valid;
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
    public ResponseEntity<Void> signIn(@RequestBody @Valid CounselorSignUpDto counselorSignUpDto){
        Counselor savedCounselor = apiCounselorService.signUp(counselorSignUpDto);
        return ResponseEntity.created(URI.create("/counselors/" + savedCounselor.getId())).build();
    }
    // 외부 공개용 상담사 마이페이지 조회
    @GetMapping("/counselors/{id}")
    public SingleRspsTemplate<CounselorInfoDto> counselorInfo(@PathVariable Long id){
        // id로 리뷰, 상담사 찾고
        // 해당 정보들을 Dto에 넣어준다
        SingleRspsTemplate<CounselorInfoDto> rspsTemplate = apiCounselorService.findCounselorAndReviewsById(id);
        return rspsTemplate;
    }
    //상담사 계정으로 본인 마이페이지 진입
    @GetMapping("/counselors/my-page")
    public SingleRspsTemplate<CounselorMyPageViewDto> viewMyPage(@SessionCounselor Counselor counselor){
        CounselorMyPageViewDto resultDto = apiCounselorService.viewMyPage(counselor);
        return new SingleRspsTemplate<>(HttpStatus.OK.value(), resultDto);
    }
    // 상담사 마이페이지 수정
    // 자기가 자기 페이지를 수정하는 것이므로, PathVariable 사용할 필요 없다.
    @PatchMapping("/counselors/my-page")
    public ResponseEntity<Void> editMyPage(@SessionCounselor Counselor counselor,
                                           @RequestBody CounselorMyPageEditDto counselorMyPageEditDto){
        apiCounselorService.editCounselor(counselor, counselorMyPageEditDto);
        return ResponseEntity.noContent().build();
    }









}













