package xyz.hugme.hugmebackend.global.init;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import xyz.hugme.hugmebackend.domain.user.counselor.Counselor;
import xyz.hugme.hugmebackend.domain.user.counselor.CounselorService;
import xyz.hugme.hugmebackend.domain.user.counselor.Field;
import xyz.hugme.hugmebackend.domain.user.counselor.Gender;
import xyz.hugme.hugmebackend.domain.user.counselor.review.CounselorReview;
import xyz.hugme.hugmebackend.domain.user.counselor.review.CounselorReviewService;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@RequiredArgsConstructor
@Component
public class CounselorInit {

    private final CounselorService counselorService;
    private final CounselorReviewService counselorReviewService;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    @PostConstruct
    public void init(){
        Set<Field> fieldsSet1 = new HashSet<>();
        fieldsSet1.add(Field.ANXIETY);
        fieldsSet1.add(Field.DEPRESSION);
        Set<Field> fieldsSet2 = new HashSet<>();
        fieldsSet2.add(Field.ELDER);
        fieldsSet2.add(Field.DEPRESSION);
        fieldsSet2.add(Field.RELATIONSHIP);

        List<String> careers1 = new ArrayList<>();
        careers1.add("성공회대졸업");
        careers1.add("뭐시기 자격증 2급");
        List<String> careers2 = new ArrayList<>();
        careers2.add("성공회대졸업");
        careers2.add("뭐시기 자격증 2급");

        Counselor counselor1 = Counselor.builder()
                .name("한현수")
                .shortIntroduction("알고보면따뜻한남자")
                .introduction("자기소개 본문")
                .gender(Gender.FEMALE)
                .fields(fieldsSet1)
                .contact("010-현수현수-0100")
                .email("cdsf@gmail.com")
                .password(passwordEncoder.encode("1234"))
                .location("어디시 어디구 어디동")
                .careers(careers1)
                .build();
        Counselor counselor2 = Counselor.builder()
                .name("이한길")
                .shortIntroduction("정열맨이한길")
                .introduction("자기소개 본문")
                .gender(Gender.FEMALE)
                .fields(fieldsSet2)
                .contact("010-한길한길-0100")
                .email("csrf@gmail.com")
                .password(passwordEncoder.encode("1234"))
                .location("어디시 어디구 어디동")
                .careers(careers2)
                .build();

        CounselorReview review1 = CounselorReview.builder()
                .rate(5)
                .content("한길선생님 감사합니다")
                .counselor(counselor2)
                .build();

        CounselorReview review2 = CounselorReview.builder()
                .rate(3)
                .content("한길선생님 너무 감사합니다")
                .counselor(counselor2)
                .build();

        counselorService.save(counselor1);
        counselorService.save(counselor2);
        counselorReviewService.save(review1); //  counselor2에 저장
        counselorReviewService.save(review2); //  counselor2에 저장
    }
}





















