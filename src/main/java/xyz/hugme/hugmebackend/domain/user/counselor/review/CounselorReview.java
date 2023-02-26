package xyz.hugme.hugmebackend.domain.user.counselor.review;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import xyz.hugme.hugmebackend.domain.user.client.Client;
import xyz.hugme.hugmebackend.domain.user.counselor.Counselor;

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

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "counselor_id", nullable = false) // 상담사 없는 상담사 리뷰는 없다. not null
    private Counselor counselor;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "client_id", nullable = false) // 누가 리뷰를 작성했는지를 저장해야 한다.
    private Client client;
    @Builder
    public CounselorReview(Integer rate, String content, Counselor counselor, Client client) {
        this.rate = rate;
        this.content = content;
        this.counselor = counselor;
        this.client = client;
    }
}
