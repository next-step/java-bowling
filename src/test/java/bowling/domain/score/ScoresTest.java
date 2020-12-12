package bowling.domain.score;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class ScoresTest {

    @DisplayName("특정 스코어 번호를 이용해 Spare인지 확인 테스트")
    @ParameterizedTest
    @CsvSource(value = {"3:7:true", "0:0:false", "9:1:true", "3:6:false"}, delimiter = ':')
    void isSpareUsingIndex(String firstValue, String secondValue, boolean expectedResult) {
        Score firstScore = Score.of(firstValue);
        Score secondScore = Score.of(secondValue);
        List<Score> scores = Arrays.asList(firstScore, secondScore);

        boolean actual = Scores.isSpare(scores);

        assertThat(actual).isEqualTo(expectedResult);
    }

    @DisplayName("초구만 진행한 경우 Spare인지 확인 테스트")
    @ParameterizedTest
    @ValueSource(strings = {"3", "4", "0"})
    void isSpare_only_one_bowl(String firstValue) {
        Score firstScore = Score.of(firstValue);
        List<Score> scores = Arrays.asList(firstScore, null);

        boolean actual = Scores.isSpare(scores);

        assertThat(actual).isFalse();
    }

    @DisplayName("Spare인지 확인 테스")
    @ParameterizedTest
    @CsvSource(value = {"5:5:true", "3:7:true", "8:1:false"}, delimiter = ':')
    void isSpare(String firstScoreString, String secondScoreString, boolean expectedResult) {
        Scores scores = NormalScores.init();
        scores.add(Score.of(firstScoreString));
        scores.add(Score.of(secondScoreString));

        boolean actualResult = scores.isSpare();

        assertThat(actualResult).isEqualTo(expectedResult);
    }

    @DisplayName("Strike인지 확인 테스트")
    @ParameterizedTest
    @CsvSource(value = {"10:true", "0:false", "7:false"}, delimiter = ':')
    void isStrike(String firstScoreString, boolean expectedResult) {
        Scores scores = NormalScores.init();
        scores.add(Score.of(firstScoreString));

        boolean actualResult = scores.isStrike();

        assertThat(actualResult).isEqualTo(expectedResult);
    }

    @DisplayName("특정 인덱스까지의 Score 합계 확인 테스트_확인하고 싶은 Score가 존재하는 경우")
    @ParameterizedTest
    @CsvSource(value = {"1:6", "2:10", "3:18", "4:18"}, delimiter = ':')
    void getScore_has_score(int bonusCount, String expectedScore) {
        Scores scores = FinalScores.init();
        scores.add(Score.of("6"));
        scores.add(Score.of("4"));
        scores.add(Score.of("8"));

        Score actualScore = scores.getScore(bonusCount);

        assertThat(actualScore).isEqualTo(Score.of(expectedScore));
    }

    @DisplayName("특정 인덱스까지의 Score 결과 확인 테스트_아예 Score가 존재하지 않는 경우")
    @Test
    void getScore_no_score() {
        Scores scores = NormalScores.init();

        Score actualScore = scores.getScore(Scores.FIRST_SCORE);

        assertThat(actualScore).isEqualTo(Score.ZERO_SCORE);
    }
}
