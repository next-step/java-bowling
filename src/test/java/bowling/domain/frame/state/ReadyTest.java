package bowling.domain.frame.state;

import static org.assertj.core.api.Assertions.*;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import bowling.domain.frame.Score;

class ReadyTest {
    @Test
    @DisplayName("첫번째 시도에서 모든 핀을 쓰러트린다면 스트라이크 상태를 반환한다.")
    void bowlWhenStrike() {
        assertThat(new Ready().bowl(10) instanceof Strike).isTrue();
    }

    @Test
    @DisplayName("첫번째 시도에서 모든 핀을 쓰러뜨리지 못하면 FirstBowl 상태를 반환한다.")
    void bowlWhenNotStrike() {
        assertThat(new Ready().bowl(5) instanceof FirstBowl).isTrue();
    }

    @Test
    @DisplayName("해당 상태에서는 해당 프레임이 진행중임을 알린다.")
    void isFinish() {
        assertThat(new Ready().isFinish()).isFalse();
    }

    @Test
    @DisplayName("Score 객체를 요구할 시에, 계산 불가한 Score 객체를 반환한다.")
    void getScore() {
        Ready ready = new Ready();
        Score score = ready.getScore();
        assertThat(score).isEqualTo(Score.needToMoreBowl());
        assertThat(score.canCalculateScore()).isFalse();
    }

    @ParameterizedTest(name = "Ready 상태는 Bonus Score를 계산해줄수 없으므로 이전 스코어 그대로 돌려준다; {1}")
    @MethodSource("provideScoreSource")
    void calculateBonusScore(Ready ready, Score score, Score expected) {
        assertThat(ready.calculateBonusScore(score)).isEqualTo(expected);
    }

    private static Stream<Arguments> provideScoreSource() {
        Ready ready = new Ready();
        return Stream.of(
            Arguments.of(ready, new Score(10, 2), new Score(10, 2)),
            Arguments.of(ready, new Score(10, 1), new Score(10, 1))
        );
    }
}
