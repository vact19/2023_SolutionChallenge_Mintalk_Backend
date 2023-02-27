package xyz.hugme.hugmebackend.api.counselor.review.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.hugme.hugmebackend.api.counselor.review.dto.ReviewDto;
import xyz.hugme.hugmebackend.api.counselor.review.service.ApiCounselorReviewService;
import xyz.hugme.hugmebackend.domain.user.client.Client;
import xyz.hugme.hugmebackend.global.auth.SessionClient;

@RequiredArgsConstructor
@RestController
public class CounselorReviewController {
    private final ApiCounselorReviewService apiCounselorReviewService;

    // 리뷰 등록
    @PostMapping("/counselors/{id}/review")
    public ResponseEntity<Void> saveReview(@PathVariable Long id, @SessionClient Client client, @RequestBody ReviewDto reviewDto){
        // 로그인한 사용자, 상담사 ID가 필요하다.
        apiCounselorReviewService.saveReview(id, client, reviewDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // 리뷰 수정
    @PatchMapping("/counselors/reviews/{id}")
    public ResponseEntity<Void> editReview(@PathVariable Long id, @SessionClient Client client, @RequestBody ReviewDto reviewDto){
        // 로그인한 사용자, 리뷰 id, 수정할 본문이 필요함.
        apiCounselorReviewService.editReview(id, client, reviewDto);
        return ResponseEntity.noContent().build();
    }

    // 리뷰 삭제
    @DeleteMapping("/counselors/reviews/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable Long id, @SessionClient Client client){
        // 로그인한 사용자, 리뷰 id가 필요하다.
        apiCounselorReviewService.deleteReview(id, client);
        return ResponseEntity.noContent().build();
    }

}















