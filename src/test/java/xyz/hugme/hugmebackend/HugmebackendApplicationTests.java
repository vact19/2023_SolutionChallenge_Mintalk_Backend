package xyz.hugme.hugmebackend;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import xyz.hugme.hugmebackend.domain.user.client.Client;
import xyz.hugme.hugmebackend.domain.user.counselor.Counselor;
import xyz.hugme.hugmebackend.domain.user.counselor.Field;
import xyz.hugme.hugmebackend.domain.user.counselor.Gender;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@SpringBootTest
class HugmebackendApplicationTests {

    @Autowired
    EntityManagerFactory emf;

    @Test
    public void emTest() throws Exception{
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        //given
        Client client = Client.builder()
                .gender(Gender.MALE)
                .name("심진수")
                .email("chj@gmailcom")
                .password("1234")
                .build();
        //when
        tx.begin();
        em.persist(client);
        em.detach(client);
        em.persist(client);
        tx.commit(); // flush
        em.close();
        //then


     }


    @Test
    public void unboundedWildCard() throws Exception{
        // <Object> 와 기능상으로는 같다.
        ArrayList arrayList = new ArrayList();
        arrayList.add(new StringBuilder());
        StringBuilder sb = (StringBuilder) arrayList.get(0);
        sb.append("sss");
        System.out.println(sb.toString());

        System.out.println(Counselor.class.getSimpleName());
    }

    @Test
    void contextLoads() {
        Field[] values = Field.values();
        List<String> descs = Arrays.stream(values)
                .map(field -> field.getDesc())
                .collect(Collectors.toList());
    }

    @Test
    void averageOfIntStream(){
        double v = IntStream.of(1, 3, 3, 3, 3, 5, 4, 4)
                .average().orElse(0.0);
        System.out.println("v = " + v);
        System.out.println(Double.valueOf(String.format("%.1f", v)));
    }

    @Test
    void favor_static_member_classes_over_non_static(){
        // 일반 inner class
        OuterClass.InnerClass innerClass = new OuterClass().new InnerClass();
        OuterClass.StaticNestedClass staticNestedClass = new OuterClass.StaticNestedClass();


        System.out.println(innerClass.getHello());
        System.out.println(innerClass.reachOuterClass());
        System.out.println(staticNestedClass.getHello());
    }
}














