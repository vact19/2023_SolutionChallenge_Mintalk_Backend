package xyz.hugme.hugmebackend;

import org.junit.jupiter.api.Test;
import xyz.hugme.hugmebackend.domain.counselor.Field;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


class HugmebackendApplicationTests {

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
}














