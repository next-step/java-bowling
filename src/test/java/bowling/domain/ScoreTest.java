package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class ScoreTest {
    @Test
    @DisplayName("값이 0 미만이면 Exception")
    void score_less_than_0() {
        assertThatIllegalArgumentException().isThrownBy(() -> new Score(-2));
    }

    @Test
    @DisplayName("값이 10 초과면 Exception")
    void score_greater_than_0() {
        assertThatIllegalArgumentException().isThrownBy(() -> new Score(20));
    }

    @Test
    @DisplayName("값이 10이면 스트라이크")
    void strike() {
        //given
        Score score = new Score(10);
        //then
        assertThat(score.isStrike()).isTrue();
    }

    @Test
    @DisplayName("값이 10 미만이면 스트라이크 아님")
    void not_strike() {
        //given
        Score score = new Score(9);
        //then
        assertThat(score.isStrike()).isFalse();
    }
}