package bowling.score;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ScoresTest {

    @DisplayName("spare인지 확인 테스트")
    @ParameterizedTest
    @CsvSource(value = {"3:7:true", "0:0:false", "9:1:true", "3:6:false"}, delimiter = ':')
    void isSpare(String firstValue, String secondValue, boolean expectedResult) {
        Score firstScore = Score.of(firstValue);
        Score secondScore = Score.of(secondValue);
        List<Score> scores = Arrays.asList(firstScore, secondScore);

        boolean actual = Scores.isSpare(scores);

        assertThat(actual).isEqualTo(expectedResult);
    }

    @DisplayName("초구만 진행한 경우 spare인지 확인 테스트")
    @ParameterizedTest
    @ValueSource(strings = {"3", "4", "0"})
    void isSpare_only_one_bowl(String firstValue) {
        Score firstScore = Score.of(firstValue);
        List<Score> scores = Arrays.asList(firstScore, null);

        boolean actual = Scores.isSpare(scores);

        assertThat(actual).isFalse();
    }
}
