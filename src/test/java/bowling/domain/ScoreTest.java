package bowling.domain;

import bowling.exception.BadScoreException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class ScoreTest {

    @ParameterizedTest
    @DisplayName("음수이거나, 300을 넘으면 score 가 invalid 하다.")
    @CsvSource(value = {"-1$false", "0$true", "1$true", "300$true", "301$false"}, delimiter = '$')
    void isValid(int score, boolean isValid) {
        assertThat(Score.isValid(score))
                .isEqualTo(isValid);
    }

    @ParameterizedTest
    @DisplayName("invalid 한 score 를 생성하려하면, BadScoreException 이 발생한다..")
    @CsvSource(value = {"-1$false", "0$true", "1$true", "300$true", "301$false"}, delimiter = '$')
    void constructor(int score, boolean isValid) {
        if (!isValid) {
            assertThatExceptionOfType(BadScoreException.class)
                    .isThrownBy(() -> new Score(score))
                    .withMessage("score 는 0 이상, 300 이하여야 합니다.");
        }
    }

    @ParameterizedTest
    @DisplayName("exportScoreDto 테스트")
    @ValueSource(ints = {0, 1, 10, 300})
    void exportScoreDto(int score) {
        assertThat(new Score(score).exportScoreDto().getScore())
                .isEqualTo(score);
    }

    @ParameterizedTest
    @DisplayName("sum 테스트")
    @CsvSource(value = {"2$3", "5$1"}, delimiter = '$')
    void isValid(int num1, int num2) {
        Score score1 = new Score(num1);
        Score score2 = new Score(num2);
        int sum = score1.sum(score2)
                .exportScoreDto()
                .getScore();
        assertThat(sum)
                .isEqualTo(num1 + num2);
    }
}
