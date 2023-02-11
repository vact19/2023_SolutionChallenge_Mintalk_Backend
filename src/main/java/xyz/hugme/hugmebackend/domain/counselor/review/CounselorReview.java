package xyz.hugme.hugmebackend.domain.counselor.review;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import xyz.hugme.hugmebackend.domain.counselor.Counselor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class CounselorReview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer rate; // 평점
    @Column(nullable = false)
    private String content; // 본문

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "counselor_id", nullable = false) // 상담사 없는 상담사 리뷰는 없다. not null
    private Counselor counselor;
}
