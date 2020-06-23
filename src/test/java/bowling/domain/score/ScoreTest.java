package bowling.domain.score;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;

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

    @DisplayName("다른 점수와의 합을 계산한다")
    @ParameterizedTest
    @CsvSource({"0, 1", "1, 9"})
    void add(int score1, int score2) {
        Score first = Score.of(score1);
        Score second = Score.of(score2);

        Score result = first.add(second);

        assertThat(result).isEqualTo(Score.of(score1 + score2));
    }

    @DisplayName("다른 점수보다 큰지 확인한다")
    @ParameterizedTest
    @CsvSource({"2, 1, true", "1, 2, false"})
    void isGreaterThan(int score1, int score2, boolean expected) {
        Score first = Score.of(score1);
        Score second = Score.of(score2);

        boolean result = first.isGreaterThan(second);

        assertThat(result).isEqualTo(expected);
    }
}
