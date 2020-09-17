package bowling.score;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

public class FinalScoresTest {

    @DisplayName("strike 시 add 테스트")
    @ParameterizedTest
    @ValueSource(strings = {"3", "9"})
    void add_strike_next_bowl(String value) {
        Scores finalScores = FinalScores.init();
        finalScores.add(ScoreTest.MAX_SCORE);

        Score bonusScore = Score.of(value);

        finalScores.add(bonusScore);

        assertThat(finalScores.hasFirstScore()).isTrue();
        assertThat(finalScores.hasSecondScore()).isFalse();
        assertThat(finalScores.getResult()).containsExactly(ScoreTest.MAX_SCORE, null, bonusScore);
    }

    @DisplayName("2구 시 add 테스트")
    @ParameterizedTest
    @CsvSource(value = {"0:2", "7:3", "4:4"}, delimiter = ':')
    void add_second_bowl(String firstValue, String secondValue) {
        Scores finalScores = FinalScores.init();
        Score firstScore = Score.of(firstValue);
        finalScores.add(firstScore);

        Score secondScore = Score.of(secondValue);
        finalScores.add(secondScore);

        assertThat(finalScores.hasFirstScore()).isTrue();
        assertThat(finalScores.hasSecondScore()).isTrue();
        assertThat(finalScores.getResult()).containsExactly(firstScore, secondScore, null);
    }

    @DisplayName("strike 시 다음 투구 가능 테스트")
    @Test
    void canBowl_strike() {
        Scores finalScores = FinalScores.init();
        Score firstScore = ScoreTest.MAX_SCORE;
        finalScores.add(firstScore);

        boolean actual = finalScores.canBowl();

        assertThat(actual).isTrue();
    }

    @DisplayName("2구까지 완료 시, 다음 투구 가능 테스트")
    @ParameterizedTest
    @CsvSource(value = {"1:2:false", "2:8:true", "9:0:false"}, delimiter = ':')
    void canBowl_isSpare(String firstValue, String secondValue, boolean expected) {
        Scores finalScores = FinalScores.init();
        Score firstScore = Score.of(firstValue);
        finalScores.add(firstScore);

        Score secondScore = Score.of(secondValue);
        finalScores.add(secondScore);

        boolean actual = finalScores.canBowl();

        assertThat(actual).isEqualTo(expected);
    }
}
