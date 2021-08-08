package bowling.domain.score;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class FrameScoreTest {
    @ValueSource(ints = {
            0, 1, 10
    })
    @ParameterizedTest
    @DisplayName("score 유효성 검사 통과")
    void ofTest(int score) {
        assertThatCode(() -> FrameScore.of(score))
                .doesNotThrowAnyException();
    }

    @ValueSource(ints = {
            -1, 11
    })
    @ParameterizedTest
    @DisplayName("score가 0 미만이거나 10 초과일떄")
    void ofInvalidTest(int score) {
        assertThatThrownBy(() -> FrameScore.of(score))
                .isInstanceOf(IllegalArgumentException.class);
    }
}