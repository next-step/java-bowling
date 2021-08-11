package bowling.view.util;

import bowling.domain.score.Score;
import bowling.domain.score.TurnScore;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class ScoreFormatTest {
    @CsvSource(value = {
            "10=X",
            "0=-",
            "1=1"
    }, delimiter = '=')
    @DisplayName("format - TurnScore 테스트")
    @ParameterizedTest
    void formatTest_TurnScore(int scoreValue, String correctFormat) {
        assertThat(
                new ScoreFormat(TurnScore.of(scoreValue)).format()
        ).isEqualTo(correctFormat);
    }

    @CsvSource(value = {
            "10=10",
            "0=0",
            "1=1"
    }, delimiter = '=')
    @DisplayName("format - Score 테스트")
    @ParameterizedTest
    void formatTest_Score(int scoreValue, String correctFormat) {
        assertThat(
                new ScoreFormat(new Score(scoreValue)).format()
        ).isEqualTo(correctFormat);
    }
}