package bowling.step2_1;

import bowling.step2_1.domain.score.Score;
import bowling.step2_1.domain.score.ScoreType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ScoreTest {

    @Test
    @DisplayName("객체 생성 테스트")
    void create() {
        Score score = Score.of(3, ScoreType.DEFAULT);
        assertThat(score).isEqualTo(Score.of(3, ScoreType.DEFAULT));
    }

    @ParameterizedTest
    @DisplayName("pitch 유효성 검사")
    @ValueSource(ints = {-1,11})
    void pitch_invalid(int inputPitch) {
        assertThatThrownBy(() -> Score.of(inputPitch, ScoreType.DEFAULT))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
