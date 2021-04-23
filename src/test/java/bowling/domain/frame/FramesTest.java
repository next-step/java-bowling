package bowling.domain.frame;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class FramesTest {

    private Frames frames;

    @BeforeEach
    void setup() {
        this.frames = new Frames();
    }

    @ParameterizedTest
    @CsvSource(value = {"10,10,10,10,10,10,10,10,10,10,10,10:1,2,3,4,5,6,7,8,9,10,10,10",
            "1,3,2,8,10,10,10,10,10,10,10,6,4,10:1,1,2,2,3,4,5,6,7,8,9,10,10,10,10"}, delimiter = ':')
    void canDetermineFrame(String points, String frames) {
        List<Integer> integerPoints = Arrays.stream(points.split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        List<Integer> frameCounts = Arrays.stream(frames.split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        SoftAssertions softAssertions = new SoftAssertions();
        for (int count = 0; count < integerPoints.size(); count++) {
            softAssertions.assertThat(this.frames.frameCount()).isEqualTo(frameCounts.get(count));
            this.frames.throwBall(integerPoints.get(count));
        }
        softAssertions.assertAll();
    }

    @ParameterizedTest
    @ValueSource(strings = {"10,10,10,10,10,10,10,10,10,10,10,10",
            "1,3,2,8,10,10,10,10,10,10,10,6,4,10"})
    void canDetermineEnd(String points) {
        List<Integer> integerPoints = Arrays.stream(points.split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        SoftAssertions softAssertions = new SoftAssertions();
        for (Integer integerPoint : integerPoints) {
            softAssertions.assertThat(frames.ended()).isFalse();
            frames.throwBall(integerPoint);
        }
        softAssertions.assertThat(frames.ended()).isTrue();
        softAssertions.assertAll();
    }
}
