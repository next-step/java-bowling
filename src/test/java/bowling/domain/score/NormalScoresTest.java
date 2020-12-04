package bowling.domain.score;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

public class NormalScoresTest {

    @DisplayName("초구 시 add 테스트")
    @ParameterizedTest
    @ValueSource(strings = {"1", "10", "3"})
    void add_first_bowl(String firstValue) {
        Scores normalScores = NormalScores.init();
        Score firstScore = Score.of(firstValue);

        normalScores.add(firstScore);

        assertThat(normalScores.hasFirstScore()).isTrue();
        assertThat(normalScores.hasSecondScore()).isFalse();
    }

    @DisplayName("2구 시 add 테스트")
    @ParameterizedTest
    @CsvSource(value = {"3:5", "5:5", "0:10"}, delimiter = ':')
    void add_second_bowl(String firstValue, String secondValue) {
        Scores normalScores = NormalScores.init();
        Score firstScore = Score.of(firstValue);
        normalScores.add(firstScore);

        Score secondScore = Score.of(secondValue);
        normalScores.add(secondScore);

        assertThat(normalScores.hasFirstScore()).isTrue();
        assertThat(normalScores.hasSecondScore()).isTrue();
        assertThat(normalScores.firstScore).isEqualTo(firstScore);
        assertThat(normalScores.secondScore).isEqualTo(secondScore);
    }

    @DisplayName("strike 시 투구 가능 테스트")
    @Test
    void canBowl_strike() {
        Scores normalScores = NormalScores.init();
        Score firstScore = ScoreTest.MAX_SCORE;
        normalScores.add(firstScore);

        boolean actual = normalScores.canBowl();

        assertThat(actual).isFalse();
    }

    @DisplayName("2구까지 완료 시, 다음 투구 가능 테스트")
    @ParameterizedTest
    @CsvSource(value = {"1:2", "0:8"}, delimiter = ':')
    void canBowl_isDone(String firstValue, String secondValue) {
        Scores normalScores = NormalScores.init();
        Score firstScore = Score.of(firstValue);
        normalScores.add(firstScore);

        Score secondScore = Score.of(secondValue);
        normalScores.add(secondScore);

        boolean actual = normalScores.canBowl();

        assertThat(actual).isFalse();
    }
}
