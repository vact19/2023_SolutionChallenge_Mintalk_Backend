package xyz.hugme.hugmebackend.global.init;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import xyz.hugme.hugmebackend.domain.user.Role;
import xyz.hugme.hugmebackend.domain.user.client.Client;
import xyz.hugme.hugmebackend.domain.user.counselor.Counselor;
import xyz.hugme.hugmebackend.domain.user.counselor.Field;
import xyz.hugme.hugmebackend.domain.user.counselor.Gender;
import xyz.hugme.hugmebackend.domain.user.counselor.review.CounselorReview;
import xyz.hugme.hugmebackend.domain.user.usersession.UserSession;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@RequiredArgsConstructor
@Component
public class CounselorInit {
    private final PasswordEncoder passwordEncoder;
    private final EntityManagerFactory emf;

    @PostConstruct
    public void init(){
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        Set<Field> fieldsSet1 = new HashSet<>();
        fieldsSet1.add(Field.ANXIETY);
        fieldsSet1.add(Field.DEPRESSION);
        Set<Field> fieldsSet2 = new HashSet<>();
        fieldsSet2.add(Field.ELDER);
        fieldsSet2.add(Field.DEPRESSION);
        fieldsSet2.add(Field.RELATIONSHIP);
        fieldsSet2.add(Field.ANXIETY);
        fieldsSet2.add(Field.YOUTH);
        List<String> careers1 = new ArrayList<>();
        careers1.add("남서울대학교 심리학과 학사 졸업");
        careers1.add("상담심리사 1급");
        careers1.add("前) 동작구 가정지원센터 심리상담사");
        careers1.add("現) 마인드심리상담소 소장");
        List<String> careers2 = new ArrayList<>();
        careers2.add("상담심리대학원 석사 졸업");
        careers2.add("심리상담 경력 13년");
        careers1.add("前) 다도움심리연구소 부부상담");
        careers2.add("現) 한국아들러협회장");

        // 상담사 생성
        Counselor counselor1 = Counselor.builder()
                .name("한현수")
                .shortIntroduction("공감의 힘을 믿습니다")
                .introduction("저는 개인이 대화를 통해 삶을 개선할 수 있음을 믿습니다. 자유롭게 자신을 표현할 수 있는 공간을 여러분과 만들고 싶습니다.")
                .gender(Gender.FEMALE)
                .fields(fieldsSet1)
                .contact("010-5313-0100")
                .email("csrf1@gmail.com")
                .password(passwordEncoder.encode("1234"))
                .location("경기도 수원시 장안구 영화동 678-90")
                .profileImageUrl("https://storage.googleapis.com/mintalk-image-storage/counselors/8e5aa698-1957-4997-8c14-dc4711eff564.jpg")
                .careers(careers1)
                .build();

        Counselor counselor2 = Counselor.builder()
                .name("이길한")
                .shortIntroduction("소외받는 사람들 옆에 서겠습니다.")
                .introduction("안녕하세요. 저는 이길한입니다. 대학 시절부터 쌓아온 다년간의 상담 경력으로 내담자 한 분 한 분에게 맞는 상담을 진행합니다.")
                .gender(Gender.MALE)
                .fields(fieldsSet2)
                .contact("010-0413-0100")
                .email("csrf2@gmail.com")
                .password(passwordEncoder.encode("1234"))
                .location("서울특별시 강남구 역삼동 123-45")
                .profileImageUrl("https://storage.googleapis.com/mintalk-image-storage/counselors/8e5aa698-2113-4997-8c14-dc4711eff564.jpg")
                .careers(careers2)
                .build();

        Counselor counselor3 = Counselor.builder()
                .name("김광석")
                .shortIntroduction("경청의 아이콘 김광석입니다.")
                .introduction("스트레스, 자존감, 인간관계에 대한 다양한 경험을 통해 여러분의 고민을 덜어드리겠습니다. ")
                .gender(Gender.FEMALE)
                .fields(fieldsSet2)
                .contact("010-3434-4343")
                .email("csrf3@gmail.com")
                .password(passwordEncoder.encode("1234"))
                .location("깊은산속 옹달샘")
                .profileImageUrl("https://storage.googleapis.com/mintalk-image-storage/counselors/8e5aa698-6532-4997-8c14-dc4711eff564.jpg")
                .careers(careers2)
                .build();
        em.persist(counselor3);

        Counselor counselor4 = Counselor.builder()
                .name("주호민")
                .shortIntroduction("안녕하세요, 저는 따뜻한 마음을 가진 상담사 주호민입니다.")
                .introduction("안녕하세요, 저는 주호민이라고 합니다. 고객들과 함께 더 나은 삶을 찾아가기 위해 항상 따뜻한 마음으로 상담에 임하고 있습니다.")
                .gender(Gender.FEMALE)
                .fields(fieldsSet2)
                .contact("010-3434-4343")
                .email("csrf4@gmail.com")
                .password(passwordEncoder.encode("1234"))
                .location("고기동 호민촌")
                .profileImageUrl("https://storage.googleapis.com/mintalk-image-storage/counselors/8e5aa698-2333-4997-8c14-dc4711eff564.jpg")
                .careers(careers2)
                .build();
        em.persist(counselor4);

        Counselor counselor5 = Counselor.builder()
                .name("김경민")
                .shortIntroduction("김경민입니다.")
                .introduction("외상, 슬픔, 중독 등의 문제가 저의 전문 분야입니다, 언제든지 상담을 통해 도움을 드릴게요.")
                .gender(Gender.MALE)
                .fields(fieldsSet2)
                .contact("010-3434-4343")
                .email("csrf5@gmail.com")
                .password(passwordEncoder.encode("1234"))
                .location("경기 김포시 고촌읍")
                .profileImageUrl("https://storage.googleapis.com/mintalk-image-storage/counselors/8e5aa698-2941-4997-8c14-dc4711eff564.jpg")
                .careers(careers2)
                .build();
        em.persist(counselor5);

        Counselor counselor6 = Counselor.builder()
                .name("도토리")
                .shortIntroduction("나를 도토리라고 부르는 것은 괜찮아 사실이니까")
                .introduction("하지만 나를 도토리라고 부르는 것만은 참을 수 없다!")
                .gender(Gender.MALE)
                .fields(fieldsSet2)
                .contact("010-3434-4343")
                .email("csrf6@gmail.com")
                .password(passwordEncoder.encode("1234"))
                .location("설악산")
                .profileImageUrl("https://storage.googleapis.com/mintalk-image-storage/counselors/8e5aa698-5313-4997-8c14-dc4711eff564.jpg")
                .careers(careers2)
                .build();
        em.persist(counselor6);

        Counselor counselor7 = Counselor.builder()
                .name("이병건")
                .shortIntroduction("이병건입니다.")
                .introduction("안녕하세요 이병건입니다. 여러분이 자기 자신과의 싸움에서 승리할 수 있도록 돕겠습니다.")
                .gender(Gender.MALE)
                .fields(fieldsSet2)
                .contact("010-3434-4343")
                .email("csrf7@gmail.com")
                .password(passwordEncoder.encode("1234"))
                .location("왕십리")
                .profileImageUrl("https://storage.googleapis.com/mintalk-image-storage/counselors/8e5aa698-6281-4997-8c14-dc4711eff564.jpg")
                .careers(careers2)
                .build();
        em.persist(counselor7);

        // 내담자 생성
        Client client1 = Client.builder()
                .gender(Gender.MALE)
                .name("내담자1")
                .email("csrf1@gmail.com")
                .password(passwordEncoder.encode("1234"))
                .build();
        Client client2 = Client.builder()
                .gender(Gender.FEMALE)
                .name("내담자2")
                .email("csrf2@gmail.com")
                .password(passwordEncoder.encode("1234"))
                .build();

        // 리뷰 생성
        CounselorReview review1 = CounselorReview.builder()
                .rate(5)
                .content("한길선생님 감사합니다")
                .counselor(counselor1)
                .client(client1)
                .build();

        // CounselorReview의 자식 엔티티 Counselor, Client cascade persist
        // persist counselor 1
        // persist client 1
        // persist review => Auto Increment 이므로 Insert SQL
        em.persist(review1);

        CounselorReview review2 = CounselorReview.builder()
                .rate(3)
                .content("현수선생님 너무 감사합니다")
                .counselor(counselor1)
                .client(client2)
                .build();

        em.persist(review2);

        CounselorReview review3 = CounselorReview.builder()
                .rate(2)
                .content("길한 선생님의 통찰력에 감동했습니다. 제가 전혀 생각해보지 못했던 접근법이었어요.")
                .counselor(counselor2)
                .client(client2)
                .build();
        em.persist(review3);

        CounselorReview review4 = CounselorReview.builder()
                .rate(1)
                .content("처음에는 긴장됐지만 상담사분의 노력 덕에 편안하게 대화할 수 있었어요.")
                .counselor(counselor2)
                .client(client2)
                .build();
        em.persist(review4);

        CounselorReview review5 = CounselorReview.builder()
                .rate(3)
                .content("제가 오랜 기간 고민했던 결정을 내리는 데 도움을 받을 수 있었습니다. 감사합니다.")
                .counselor(counselor2)
                .client(client2)
                .build();
        em.persist(review5);

        CounselorReview review6 = CounselorReview.builder()
                .rate(5)
                .content("이길한 선생님에게 상담받고 저의 성공시대가 시작되었습니다.")
                .counselor(counselor2)
                .client(client2)
                .build();
        em.persist(review6);

        CounselorReview review7 = CounselorReview.builder()
                .rate(3)
                .content("제 이야기를 들어주셔서 감사합니다")
                .counselor(counselor2)
                .client(client2)
                .build();
        em.persist(review7);

        CounselorReview review8 = CounselorReview.builder()
                .rate(4)
                .content("제 삶에 대해서 다시 생각해볼 수 있었어요")
                .counselor(counselor2)
                .client(client2)
                .build();
        em.persist(review8);

        CounselorReview review9 = CounselorReview.builder()
                .rate(1)
                .content("제 말을 듣지 않는군요")
                .counselor(counselor2)
                .client(client2)
                .build();
        em.persist(review9);

        CounselorReview review10 = CounselorReview.builder()
                .rate(1)
                .content("경청해 주셔서 감사합니다")
                .counselor(counselor2)
                .client(client2)
                .build();
        em.persist(review10);

        // 세션 생성
        UserSession userSession1 = createUserSessionCounselor();UserSession userSession2 = createUserSessionCounselor();UserSession userSession3 = createUserSessionCounselor();
        UserSession userSession4 = createUserSessionCounselor();UserSession userSession5 = createUserSessionCounselor();UserSession userSession6 = createUserSessionCounselor();
        UserSession userSession7 = createUserSessionCounselor();

        UserSession userSession8 = createUserSessionClient();
        UserSession userSession9 = createUserSessionClient();

        em.persist(userSession1);em.persist(userSession2);em.persist(userSession3);em.persist(userSession4);
        em.persist(userSession5);em.persist(userSession6);em.persist(userSession7);em.persist(userSession8);em.persist(userSession9);

        counselor1.setUserSession(userSession1);counselor2.setUserSession(userSession2);
        counselor3.setUserSession(userSession3);counselor4.setUserSession(userSession4);
        counselor5.setUserSession(userSession5);counselor6.setUserSession(userSession6);
        counselor7.setUserSession(userSession7);

        client1.setUserSession(userSession8);
        client2.setUserSession(userSession9);

        tx.commit();
        em.close();
    }

    private UserSession createUserSessionCounselor() {
        return UserSession.builder()
                .expirationDate(LocalDateTime.now())
                .role(Role.COUNSELOR)
                .build();
    }

    private UserSession createUserSessionClient() {
        return UserSession.builder()
                .expirationDate(LocalDateTime.now())
                .role(Role.CLIENT)
                .build();
    }
}





















