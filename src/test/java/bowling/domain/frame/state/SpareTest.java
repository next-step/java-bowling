package bowling.domain.frame.state;

import static org.assertj.core.api.Assertions.*;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import bowling.domain.frame.Score;

class SpareTest {
    @Test
    @DisplayName("조건을 만족하지 않는 핀의 갯수를 넣어줄 때 예외를 던진다.")
    void createWithInvalidPins() {
        assertThatThrownBy(() -> new Spare(new Pins(4), new Pins(5)))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("스페어의 조건을 만족하지 않습니다");
    }

    @Test
    @DisplayName("스페어 상태에서는 볼링을 시도할 시 예외를 던진다.")
    void bowl() {
        assertThatThrownBy(() -> new Spare(new Pins(4), new Pins(6)).bowl(10))
            .isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    @DisplayName("스페어 상태에서는 해당 프레임의 종료를 알린다.")
    void isFinish() {
        assertThat(new Spare(new Pins(4), new Pins(6)).isFinish()).isTrue();
    }

    @Test
    @DisplayName("Score 객체를 요구할 시에, 기본 점수는 구해졌지만 보너스 점수를 구할 수 없는 상태의 score를 반환한다.")
    void getScore() {
        Spare spare = new Spare(new Pins(4), new Pins(6));
        Score score = spare.getScore();
        assertThat(score).isEqualTo(new Score(10, 1));
        assertThat(score.canCalculateScore()).isFalse();
    }

    @ParameterizedTest(name = "주어진 스코어에 따라 보너스 점수를 계산해주어야 한다; {1}")
    @MethodSource("provideScoreSource")
    void calculateBonusScore(Spare spare, Score score, Score expected) {
        assertThat(spare.calculateBonusScore(score)).isEqualTo(expected);
    }

    private static Stream<Arguments> provideScoreSource() {
        Spare spare = new Spare(new Pins(4), new Pins(6));
        return Stream.of(
            Arguments.of(spare, new Score(10, 2), new Score(20, 0)),
            Arguments.of(spare, new Score(10, 1), new Score(14, 0))
        );
    }
}
