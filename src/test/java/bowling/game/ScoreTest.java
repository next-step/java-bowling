package bowling.game;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class ScoreTest {

    private static Stream<Arguments> provideScoreAndResult() {
        return Stream.of(
                Arguments.of(Score.ofStrike(), false),
                Arguments.of(Score.ofMiss(9), true)
        );
    }

    @DisplayName("보너스 점수 계산 완료 여부를 반환한다.")
    @ParameterizedTest
    @MethodSource("provideScoreAndResult")
    void canCalculateScore(Score score, boolean result) {
        assertThat(score.canCalculateScore()).isEqualTo(result);
    }

    @DisplayName("점수를 더하고 보너스 합산 가능 횟수를 1 줄인다.")
    @Test
    void addBonusScore() {
        Score score = new Score(3, 1);

        Score afterAdd = score.addBonusScore(4);

        assertThat(afterAdd.getScore()).isEqualTo(7);
        assertThat(afterAdd.canCalculateScore()).isEqualTo(true);
    }

    @DisplayName("보너스 합산 가능 횟수가 0 일때 더 하면 IllegalStatementException throw")
    @Test
    void addBonusScoreThrowException() {
        Score score = new Score(3, 0);

        assertThatThrownBy(() -> score.addBonusScore(4))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("더 이상 보너스 점수를 더할 수 없습니다.");
    }
}