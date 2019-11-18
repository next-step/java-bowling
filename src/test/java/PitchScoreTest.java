import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PitchScoreTest {

    @ParameterizedTest
    @ValueSource(ints = {0, 10})
    void 투구점수_생성(int score) {
        PitchScore pitchScore = new PitchScore(score);
        assertThat(pitchScore).isEqualTo(new PitchScore(score));
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 11})
    void 올바르지않은_투구점수_생성시(int score) {
        assertThatThrownBy(() -> new PitchScore(score))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(score + "는 올바른 점수가 아닙니다.");
    }
}