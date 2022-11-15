package bowling.domain.frame.state;

import static org.assertj.core.api.Assertions.*;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import bowling.domain.frame.Score;

class StrikeTest {
    @Test
    @DisplayName("스트라이크 상태에서는 볼링을 시도할 시 예외를 던진다.")
    void bowl() {
        assertThatThrownBy(() -> new Strike().bowl(10))
            .isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    @DisplayName("스트라이크 상태에서는 해당 프레임의 종료를 알린다.")
    void isFinish() {
        assertThat(new Strike().isFinish()).isTrue();
    }

    @Test
    @DisplayName("Score 객체를 요구할 시에, 기본 점수는 구해졌지만 보너스 점수를 구할 수 없는 상태의 score를 반환한다.")
    void getScore() {
        Strike strike = new Strike();
        Score score = strike.getScore();
        assertThat(score).isEqualTo(new Score(10, 2));
        assertThat(score.canCalculateScore()).isFalse();
    }

    @ParameterizedTest(name = "주어진 스코어에 따라 보너스 점수를 계산해주어야 한다; {1}")
    @MethodSource("provideScoreSource")
    void calculateBonusScore(Strike strike, Score score, Score expected) {
        assertThat(strike.calculateBonusScore(score)).isEqualTo(expected);
    }

    private static Stream<Arguments> provideScoreSource() {
        Strike strike = new Strike();
        return Stream.of(
            Arguments.of(strike, new Score(10, 2), new Score(20, 1)),
            Arguments.of(strike, new Score(10, 1), new Score(20, 0))
        );
    }
}
