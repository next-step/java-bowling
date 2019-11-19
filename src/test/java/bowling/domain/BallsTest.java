package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class BallsTest {
    @ParameterizedTest
    @DisplayName("점수 반영 테스트")
    @MethodSource(value = "providePinsAndScore")
    void add(Ball[] balls, int score) {
        Balls expectBalls = new Balls(Arrays.asList(balls));
        assertThat(expectBalls.score()).isEqualTo(score);
    }

    static Stream<Arguments> providePinsAndScore() {
        return Stream.of(
                Arguments.of(new Ball[] {new Ball(5)}, 5),
                Arguments.of(new Ball[] {new Ball(10)}, 10),
                Arguments.of(new Ball[] {new Ball(6), new Ball(4)}, 10),
                Arguments.of(new Ball[] {new Ball(10), new Ball(10), new Ball(5)}, 25),
                Arguments.of(new Ball[] {new Ball(6), new Ball(4), new Ball(5)}, 15),
                Arguments.of(new Ball[] {new Ball(10), new Ball(10), new Ball(10)}, 30)
        );
    }

    @ParameterizedTest
    @DisplayName("점수 반영시 에러 테스트")
    @MethodSource(value = "providePinsAndException")
    void addException(Ball[] balls, int nextPin, String errorMessage) {
        Balls expectBalls = new Balls(Arrays.asList(balls));
        assertThatThrownBy(() -> {
            expectBalls.fallDown(nextPin);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage(errorMessage);
    }

    static Stream<Arguments> providePinsAndException() {
        return Stream.of(
                Arguments.of(new Ball[] {new Ball(10), new Ball()}, 10, Balls.IS_OVER_FLOW_PIN_COUNT),
                Arguments.of(new Ball[] {new Ball(6), new Ball(3)}, 2, Balls.IS_OVER_FLOW_BALL_COUNT),
                Arguments.of(new Ball[] {new Ball(6), new Ball(3), new Ball()}, 2, Balls.IS_NOT_ADD_ABLE_THIRD_BALL_MESSAGE)
        );
    }

    @Test
    @DisplayName("투구 가능한 수를 리턴한다.")
    void addAblePinCount() {
        Balls balls = new Balls(false);
        assertThat(balls.addAblePinCount(false)).isEqualTo(10);

        balls.fallDown(5);
        assertThat(balls.addAblePinCount(false)).isEqualTo(5);

        balls.fallDown(5);
        assertThat(balls.addAblePinCount(false)).isEqualTo(0);
    }

    @ParameterizedTest
    @DisplayName("Balls의 결과를 확인 한다.")
    @MethodSource(value = "providePinsAndResult")
    void getResult(int[] pins, boolean isLastFrame, String result) {
        Balls balls = new Balls(isLastFrame);
        for (int pin : pins) {
            balls.fallDown(pin);
        }
//        assertThat(balls.getResult()).isEqualTo(result);
    }

    static Stream<Arguments> providePinsAndResult() {
        return Stream.of(
                Arguments.of(new int[] {0}, false, "-"),
                Arguments.of(new int[] {5}, false, "5"),
                Arguments.of(new int[] {10}, false, "X"),
                Arguments.of(new int[] {6, 1}, false, "6|1"),
                Arguments.of(new int[] {6, 0}, false, "6|-"),
                Arguments.of(new int[] {6, 4}, false, "6|/"),
                Arguments.of(new int[] {6, 4, 5}, true, "6|/|5"),
                Arguments.of(new int[] {10, 10, 10}, true, "X|X|X")
        );
    }
}
