package xyz.hugme.hugmebackend.api.counselor.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.hugme.hugmebackend.api.common.RspsTemplate;
import xyz.hugme.hugmebackend.api.common.SingleRspsTemplate;
import xyz.hugme.hugmebackend.api.counselor.dto.AddCounselorDto;
import xyz.hugme.hugmebackend.api.counselor.dto.CounselorInfoDto;
import xyz.hugme.hugmebackend.api.counselor.dto.CounselorListDto;
import xyz.hugme.hugmebackend.api.counselor.service.ApiCounselorService;
import xyz.hugme.hugmebackend.domain.counselor.Counselor;
import xyz.hugme.hugmebackend.domain.counselor.CounselorService;

import java.net.URI;

@RequiredArgsConstructor
@RestController
public class CounselorController {

    private final ApiCounselorService apiCounselorService;
    private final CounselorService counselorService;

    // 테스트용 메소드. 나중에 조회기능 만들때 이거 바꾸면 될 듯
    @GetMapping
    public ResponseEntity<RspsTemplate<CounselorListDto>> getList(){
        RspsTemplate<CounselorListDto> rspsTemplate = apiCounselorService.findAll();
        return ResponseEntity.ok(rspsTemplate);
    }

    // 상담사 회원가입
    @PostMapping("/counselors")
    public ResponseEntity<Void> signIn(@RequestBody AddCounselorDto addCounselorDto){
        Counselor savedCounselor = counselorService.save(addCounselorDto.toEntity());
        return ResponseEntity.created(URI.create("/counselors/" + savedCounselor.getId())).build();
    }

    @GetMapping("/counselors/{id}")
    public SingleRspsTemplate<CounselorInfoDto> counselorInfo(@PathVariable Long id){
        // id로 리뷰, 상담사 찾고
        // 해당 정보들을 Dto에 넣어준다
        SingleRspsTemplate<CounselorInfoDto> rspsTemplate = apiCounselorService.findCounselorAndReviewsById(id);

        return rspsTemplate;
    }



}













