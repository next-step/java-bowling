package bowling.domain.frame;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class NormalFramesTest {

    private NormalFrames normalFrames;

    @BeforeEach
    void setup(){
        normalFrames = new NormalFrames();
    }

    @ParameterizedTest
    @ValueSource(strings = {"1:2:1:2:10:10:10:10:10:10:10",
            "10:10:10:10:10:10:10:10:1:8",
            "1:1:1:1:1:1:1:1:1:1:1:1:1:1:1:1:1:1"})
    void canThrowBalls(String values) {
        List<Integer> integerList = Arrays.stream(values.split(":"))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        SoftAssertions softAssertions = new SoftAssertions();
        for(Integer integer:integerList){
            softAssertions.assertThat(normalFrames.ended()).isFalse();
            normalFrames.throwBall(integer);
        }

        softAssertions.assertThat(normalFrames.ended()).isTrue();
        softAssertions.assertAll();
    }

}
