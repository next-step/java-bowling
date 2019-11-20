package frame;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class LastFrameTest {

    private static Stream<Arguments> getLastBowling() {
        return Stream.of(
                Arguments.of(Arrays.asList(1), false),
                Arguments.of(Arrays.asList(10), false),
                Arguments.of(Arrays.asList(10, 10), false),
                Arguments.of(Arrays.asList(10, 10, 10), true),
                Arguments.of(Arrays.asList(1, 1), true),
                Arguments.of(Arrays.asList(1, 9), false),
                Arguments.of(Arrays.asList(1, 9, 10), true),
                Arguments.of(Arrays.asList(10, 9), false),
                Arguments.of(Arrays.asList(10, 9, 1), true)
        );
    }

    @ParameterizedTest
    @MethodSource("getLastBowling")
    void isFull(List<Integer> scores, boolean answer) {
        LastFrame lastFrame = LastFrame.init();
        for (int score : scores) {
            lastFrame.bowling(score);
        }
        assertThat(lastFrame.isFull()).isEqualTo(answer);
    }

}