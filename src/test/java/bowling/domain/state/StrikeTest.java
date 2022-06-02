package bowling.domain.state;

import bowling.domain.score.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class StrikeTest {

    @Test
    @DisplayName("보너스 투구 시 점수 계산")
    void bonusBowling() {
        assertThat(Ready.of(10).bonusBowling(5).totalScore()).isEqualTo(15);
    }

    @ParameterizedTest(name = "현재 프레임이 Strike 인 경우 다음 프레임(Miss, Spare) 포함 점수 계산 - {0}")
    @MethodSource("calculateScoreCondition")
    void calculateScore(State nextState, int expectedScore) {
        Score before = new Strike(10).createScore();
        Score after = nextState.calculateScore(before);

        assertAll(() -> assertThat(after.left()).isEqualTo(0),
                () -> assertThat(after.getScore()).isEqualTo(expectedScore));
    }

    private static Stream<Arguments> calculateScoreCondition() {
        return Stream.of(
                Arguments.of(Ready.of(4).bowling(4), 18),
                Arguments.of(Ready.of(5).bowling(5), 20)
        );
    }
}
