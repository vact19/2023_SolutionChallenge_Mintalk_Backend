package xyz.hugme.hugmebackend.global.init;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import xyz.hugme.hugmebackend.domain.user.client.Client;
import xyz.hugme.hugmebackend.domain.user.client.ClientService;
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
    private final ClientService clientService;
    private final PasswordEncoder passwordEncoder;

    @PostConstruct
    @Transactional
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

        // 상담사 생성
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

        // 내담자 생성
        Client client1 = Client.builder()
                .gender(Gender.MALE)
                .name("내담자1")
                .email("naedamza1@gmail.com")
                .password(passwordEncoder.encode("1234"))
                .build();
        Client client2 = Client.builder()
                .gender(Gender.FEMALE)
                .name("내담자2")
                .email("naedamza2@gmail.com")
                .password(passwordEncoder.encode("1234"))
                .build();

        // 리뷰 생성
        CounselorReview review1 = CounselorReview.builder()
                .rate(5)
                .content("한길선생님 감사합니다")
                .counselor(counselor1)
                .client(client1)
                .build();

        CounselorReview review2 = CounselorReview.builder()
                .rate(3)
                .content("한길선생님 너무 감사합니다")
                .counselor(counselor2)
                .client(client2)
                .build();


        // CounselorReview의 자식 엔티티 Counselor, Client cascade persist
        counselorReviewService.save(review1); //  counselor2에 저장

        // persist counselor 1
        // persist client 1
        // persist review => Auto Increment 이므로 Insert SQL
        counselorReviewService.save(review2); //  counselor2에 저장
        // persist counselor 1 => 에러발생 detached entity passed to persist: xyz.hugme.hugmebackend.domain.user.counselor.Counselor
    }
}





















