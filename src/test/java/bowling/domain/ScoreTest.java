package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class ScoreTest {

    @ParameterizedTest
    @DisplayName("음수이면 score 가 invalid 하다.")
    @CsvSource(value = {"-1$false", "0$true", "1$true"}, delimiter = '$')
    void isValid(int score, boolean expected) {
        assertThat(new Score(score).isValid())
                .isEqualTo(expected);
    }

    @ParameterizedTest
    @DisplayName("exportScoreDto 테스트")
    @ValueSource(ints = {-10, -1, 0, 1, 10})
    void exportScoreDto(int score) {
        assertThat(new Score(score).exportScoreDto().getScore())
                .isEqualTo(score);
    }
}
