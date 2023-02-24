package xyz.hugme.hugmebackend;

import org.junit.jupiter.api.Test;
import xyz.hugme.hugmebackend.domain.user.counselor.Counselor;
import xyz.hugme.hugmebackend.domain.user.counselor.Field;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


class HugmebackendApplicationTests {

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














