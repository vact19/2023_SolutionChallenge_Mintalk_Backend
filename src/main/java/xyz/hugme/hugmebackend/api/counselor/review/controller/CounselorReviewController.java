package xyz.hugme.hugmebackend.api.counselor.review.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.hugme.hugmebackend.api.counselor.review.dto.SaveReviewDto;
import xyz.hugme.hugmebackend.api.counselor.review.service.ApiCounselorReviewService;

@RequiredArgsConstructor
@RestController
public class CounselorReviewController {
    private final ApiCounselorReviewService apiCounselorReviewService;

    // 리뷰 등록
    @PostMapping("/counselors/{id}/review")
    public ResponseEntity<Void> saveReview(@PathVariable Long id, @RequestBody SaveReviewDto saveReviewDto){
        // 로그인한 사용자, 상담사 ID
        // DTO는 본문만. 리뷰랑 별점.
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // 리뷰 수정
    @PatchMapping("/counselors/{counselorId}/reviews/{reviewId}")
    public ResponseEntity<Void> editReview(@PathVariable Long counselorId, @PathVariable Long reviewId){
        return ResponseEntity.noContent().build();
    }

    // 리뷰 삭제
    @DeleteMapping("/counselors/{counselorId}/reviews/{reviewId}")
    public ResponseEntity<Void> deleteReview(@PathVariable Long counselorId, @PathVariable Long reviewId){

        return ResponseEntity.noContent().build();
    }

}















