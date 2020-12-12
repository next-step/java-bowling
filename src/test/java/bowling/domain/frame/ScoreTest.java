package bowling.domain.frame;

import bowling.exception.BadScoreException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class ScoreTest {
    @ParameterizedTest
    @DisplayName("음수이거나, 300을 넘으면, BadScoreException 이 발생한다..")
    @ValueSource(ints = {-1, 301})
    void constructor(int score) {
        assertThatExceptionOfType(BadScoreException.class)
                .isThrownBy(() -> new Score(score))
                .withMessage("score 는 0 이상, 300 이하여야 합니다.");
    }

    @ParameterizedTest
    @DisplayName("exportScoreDto 테스트")
    @ValueSource(ints = {0, 1, 10, 300})
    void exportScoreDto(int score) {
        assertThat(new Score(score).exportScoreDto().getScore())
                .isEqualTo(score);
    }
}
