package xyz.hugme.hugmebackend;

import org.junit.jupiter.api.Test;
import xyz.hugme.hugmebackend.domain.counselor.Field;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


class HugmebackendApplicationTests {

    @Test
    void contextLoads() {
        Field[] values = Field.values();
        List<String> descs = Arrays.stream(values)
                .map(field -> field.getDesc())
                .collect(Collectors.toList());


    }

}














