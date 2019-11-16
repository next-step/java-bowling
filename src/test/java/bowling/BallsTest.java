package bowling;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class BallsTest {
    @ParameterizedTest
    @DisplayName("점수 반영 테스트")
    @MethodSource(value = "providePinsAndScore")
    void add(int[] pins, boolean isLastFrame, int score) {
        Balls balls = new Balls();
        for (int pin : pins) {
            balls.add(pin, isLastFrame);
        }
        assertThat(balls.score()).isEqualTo(score);
    }

    static Stream<Arguments> providePinsAndScore() {
        return Stream.of(
                Arguments.of(new int[] {5}, false, 5),
                Arguments.of(new int[] {10}, false, 10),
                Arguments.of(new int[] {6, 4}, false, 10),
                Arguments.of(new int[] {10, 10, 5}, true, 25),
                Arguments.of(new int[] {6, 4, 5}, true, 15),
                Arguments.of(new int[] {10, 10, 10}, true, 30)
        );
    }

    @ParameterizedTest
    @DisplayName("점수 반영시 에러 테스트")
    @MethodSource(value = "providePinsAndException")
    void addException(int[] pins, boolean isLastFrame, String errorMessage) {
        Balls balls = new Balls();
        assertThatThrownBy(() -> {
            for (int pin : pins) {
                balls.add(pin, isLastFrame);
            }
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage(errorMessage);
    }

    static Stream<Arguments> providePinsAndException() {
        return Stream.of(
                Arguments.of(new int[] {10, 10}, false, Balls.IS_OVER_FLOW_PIN_COUNT),
                Arguments.of(new int[] {6, 3, 2}, false, Balls.IS_OVER_FLOW_BALL_COUNT),
                Arguments.of(new int[] {6, 3, 2}, true, Balls.IS_NOT_ADD_ABLE_THIRD_BALL_MESSAGE)
        );
    }

    @Test
    @DisplayName("투구 가능한 수를 리턴한다.")
    void addAblePinCount() {
        Balls balls = new Balls();
        assertThat(balls.addAblePinCount()).isEqualTo(10);

        balls.add(5, false);
        assertThat(balls.addAblePinCount()).isEqualTo(5);

        balls.add(5, true);
        assertThat(balls.addAblePinCount()).isEqualTo(10);
    }
}
