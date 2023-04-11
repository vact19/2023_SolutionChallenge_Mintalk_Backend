package xyz.hugme.hugmebackend.domain.user.counselor.review;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import xyz.hugme.hugmebackend.domain.common.BaseTimeEntity;
import xyz.hugme.hugmebackend.domain.user.client.Client;
import xyz.hugme.hugmebackend.domain.user.counselor.Counselor;
import xyz.hugme.hugmebackend.global.exception.BusinessException;
import xyz.hugme.hugmebackend.global.exception.ErrorCode;

import javax.persistence.*;
import java.util.Objects;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class CounselorReview extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int rate; // 평점
    @Column(nullable = false)
    private String content; // 본문

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "counselor_id", nullable = false) // 상담사 없는 상담사 리뷰는 없다. not null
    private Counselor counselor;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "client_id", nullable = false) // 누가 리뷰를 작성했는지를 저장해야 한다.
    private Client client;

    public void updateReview(int rate, String content){
        this.rate = rate;
        this.content = content;
    }

    public void validateUpdateReview(Long clientId){
        if (!Objects.equals(client.getId(), clientId))
            throw new BusinessException(ErrorCode.CLIENT_NOT_AUTHORIZED);
    }

    @Builder
    public CounselorReview(Integer rate, String content, Counselor counselor, Client client) {
        this.rate = rate;
        this.content = content;
        this.counselor = counselor;
        this.client = client;
    }
}
