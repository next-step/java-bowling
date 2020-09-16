package bowling.score;

import bowling.exception.ExceptionMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

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
}
