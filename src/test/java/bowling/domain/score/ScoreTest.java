package bowling.domain.score;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ScoreTest {

    @DisplayName("점수가 0 이상, 10 이하이면 객체 생성 완료")
    @ParameterizedTest
    @ValueSource(ints = {0, 5, 10})
    void of(int input) {
        assertThatCode(() -> Score.of(input))
                .doesNotThrowAnyException();
    }

    @DisplayName("점수가 0 이상, 10 이하가 아니면 IllegalArgumentException")
    @ParameterizedTest
    @ValueSource(ints = {-1, 11})
    void of_score_notBetween0And10(int input) {
        assertThatThrownBy(() -> Score.of(input))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
