package bowling.score;

import bowling.exception.ExceptionMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;

public class ScoreTest {
    public static final Score MAX_SCORE = Score.of("10");

    @DisplayName("생성 테스트")
    @ParameterizedTest
    @ValueSource(strings = {"3", "7", "21"})
    void test(String scoreValue) {

        assertThatCode(() -> Score.of(scoreValue))
                .doesNotThrowAnyException();
    }

    @DisplayName("투구값 범위 넘겼을 시, 생성 테스트")
    @ParameterizedTest
    @ValueSource(strings = {"-1", "-20"})
    void of_invalid_score(String scoreValue) {
        String exceptionMessage = ExceptionMessage.INVALID_SCORE_VALUE;

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> Score.of(scoreValue))
                .withMessage(exceptionMessage);

    }

    @DisplayName("strike에 해당하는 점수인지 확인 테스트")
    @ParameterizedTest
    @CsvSource(value = {"10:true", "0:false", "3:false"}, delimiter = ':')
    void isStrike(String value, boolean expected) {
        Score score = Score.of(value);

        boolean actual = score.isStrike();

        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("gutter에 해당하는 점수인지 확인 테스트")
    @ParameterizedTest
    @CsvSource(value = {"7:false", "0:true", "3:false"}, delimiter = ':')
    void isGutter(String value, boolean expected) {
        Score score = Score.of(value);

        boolean actual = score.isGutter();

        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("두 개의 Score의 합 결과 테스트")
    @ParameterizedTest
    @CsvSource(value = {"1:2:3", "4:1:5", "10:3:13", "7:9:16"}, delimiter = ':')
    void sum(String firstValue, String secondValue, String expectedValue) {
        Score firstScore = Score.of(firstValue);
        Score secondScore = Score.of(secondValue);

        Score resultScore = firstScore.sum(secondScore);

        Score expectedScore = Score.of(expectedValue);
        assertThat(resultScore).isEqualTo(expectedScore);
    }
}
