package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
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
                Arguments.of(new Ball[]{new Ball(5)}, 5),
                Arguments.of(new Ball[]{new Ball(10)}, 10),
                Arguments.of(new Ball[]{new Ball(6), new Ball(4)}, 10),
                Arguments.of(new Ball[]{new Ball(10), new Ball(10), new Ball(5)}, 25),
                Arguments.of(new Ball[]{new Ball(6), new Ball(4), new Ball(5)}, 15),
                Arguments.of(new Ball[]{new Ball(10), new Ball(10), new Ball(10)}, 30)
        );
    }

    @Test
    @DisplayName("점수 반영시 핀 수 초과 에러 테스트")
    void fallDownIsOverFlowPinCountException() {
        Ball[] balls = new Ball[]{new Ball(10), new Ball()};
        int nextPin = 10;
        Balls expectBalls = new Balls(Arrays.asList(balls));

        assertThatThrownBy(() -> {
            expectBalls.fallDown(nextPin);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage(Balls.IS_OVER_FLOW_PIN_COUNT);
    }

    @Test
    @DisplayName("점수 반영시 쓰러트릴 수 있는 횟 수 초과 에러 테스트")
    void fallDownIsOverFlowBallCountException() {
        Ball[] balls = new Ball[]{new Ball(6), new Ball(3)};
        int nextPin = 2;
        Balls expectBalls = new Balls(Arrays.asList(balls));

        assertThatThrownBy(() -> {
            expectBalls.fallDown(nextPin);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage(Balls.IS_OVER_FLOW_BALL_COUNT);
    }

    @Test
    @DisplayName("마지막 프레임에서 스트라이크 또는 스페어가 아닐때 에러 테스트")
    void fallDownIsNotAddAbleThirdBall() {
        Ball[] balls = new Ball[]{new Ball(6), new Ball(3), new Ball()};
        int nextPin = 2;
        Balls expectBalls = new Balls(Arrays.asList(balls));

        assertThatThrownBy(() -> {
            expectBalls.fallDown(nextPin);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage(Balls.IS_NOT_ADD_ABLE_THIRD_BALL_MESSAGE);
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

    @Test
    @DisplayName("size가 다른지 확인 한다.")
    void isNotSame() {
        List<Ball> ballList = Arrays.asList(new Ball(5), new Ball(5));
        Balls balls = new Balls(ballList);
        assertThat(balls.isNotSameSize(ballList.size() - 1)).isTrue();
    }
}
