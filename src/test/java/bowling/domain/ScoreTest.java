package bowling.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class ScoreTest {
    @Test
    @DisplayName("Score 객체 생성을 확인한다")
    void checkedScoreObjectGenerate() {
        // when & then
        assertThat(new Score(1, 0)).isNotNull();
    }

    @ParameterizedTest(name = "잘못된 점수 {0}인 경우, 예외처리를 한다")
    @ValueSource(ints = {-1, 31})
    void exceptionWrongScore(int value) {
        // given
        int remainingPitches = 2;

        // when & then
        assertThatThrownBy(() -> new Score(value, remainingPitches))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("현재 점수를 확인한다")
    void checkedNowScore() {
        // given
        Score score = new Score(10, 1);

        // when
        Score result = score.bowl(new Score(5, 0));

        // then
        assertThat(result.getValue()).isEqualTo(15);
    }
}