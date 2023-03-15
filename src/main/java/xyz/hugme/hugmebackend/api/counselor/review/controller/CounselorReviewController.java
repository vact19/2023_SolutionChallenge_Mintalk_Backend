package xyz.hugme.hugmebackend.api.counselor.review.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import xyz.hugme.hugmebackend.api.common.RspsTemplate;
import xyz.hugme.hugmebackend.api.common.SingleRspsTemplate;
import xyz.hugme.hugmebackend.api.common.UserStatus;
import xyz.hugme.hugmebackend.api.counselor.review.dto.CounselorReviewDto;
import xyz.hugme.hugmebackend.api.counselor.review.service.ApiCounselorReviewService;
import xyz.hugme.hugmebackend.domain.user.client.Client;
import xyz.hugme.hugmebackend.global.auth.SessionClient;
import xyz.hugme.hugmebackend.global.auth.SessionStatus;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class CounselorReviewController {
    private final ApiCounselorReviewService apiCounselorReviewService;

    // 리뷰 등록
    @PostMapping("/counselors/{id}/reviews")
    public SingleRspsTemplate<CounselorReviewDto.Response> saveReview(@PathVariable Long id, @SessionStatus UserStatus userStatus,
                                                                      @SessionClient Client client, @RequestBody CounselorReviewDto.Request reviewRequestDto){
        // 등록하려면 로그인한 사용자, 상담사 ID가 필요하다.
        CounselorReviewDto.Response response = apiCounselorReviewService.saveReview(id, client, reviewRequestDto);
        return new SingleRspsTemplate<>(HttpStatus.CREATED.value(), response, userStatus);
    }

    // 리뷰 수정
    @PatchMapping("/counselors/reviews/{id}")
    public SingleRspsTemplate<CounselorReviewDto.Response> editReview(@PathVariable Long id, @SessionStatus UserStatus userStatus,
                                                                      @SessionClient Client client, @RequestBody CounselorReviewDto.Request reviewRequestDto){
        // 수정에 로그인한 사용자, 리뷰 id, 수정할 본문이 필요함.
        CounselorReviewDto.Response response = apiCounselorReviewService.editReview(id, client, reviewRequestDto);

        return new SingleRspsTemplate<>(HttpStatus.OK.value(), response, userStatus);
    }

    // 리뷰 삭제
    @DeleteMapping("/counselors/reviews/{id}")
    public SingleRspsTemplate<?> deleteReview(@PathVariable Long id, @SessionStatus UserStatus userStatus,
                                             @SessionClient Client client){
        // 삭제에 로그인한 사용자, 리뷰 id가 필요하다.
        apiCounselorReviewService.deleteReview(id, client);
        return new SingleRspsTemplate<>(HttpStatus.OK.value(), userStatus);
    }

    // '특정 상담사'에게 '내가 쓴 리뷰'의 리스트만 건네기.
    // counselors/{id}/my-reviews
    @GetMapping("/counselors/{id}/my-reviews")
    public RspsTemplate<CounselorReviewDto.Response> getMyReview(@PathVariable Long id, @SessionStatus UserStatus userStatus,
                                                                 @SessionClient Client client){
        List<CounselorReviewDto.Response> reviewDtoList = apiCounselorReviewService.getReviewByClientAndCounselorId(client, id);

        return new RspsTemplate<>(HttpStatus.OK.value(), reviewDtoList, userStatus);
    }


}















