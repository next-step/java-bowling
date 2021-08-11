package bowling.domain.score.framescore;

import bowling.domain.score.TurnScoreTest;
import bowling.domain.score.TurnScores;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class FrameScoreTest {
    @CsvSource(value = {
            "5,5,6=16"
    }, delimiter = '=')
    @DisplayName("합산 테스트")
    @ParameterizedTest
    void ctorTest(String strScores, int correct) {
        TurnScores turnScores = new TurnScores(
                TurnScoreTest.toFrameScores(strScores)
        );
        assertThat(
                new FrameScore(turnScores).value()
        ).isEqualTo(correct);
    }
}