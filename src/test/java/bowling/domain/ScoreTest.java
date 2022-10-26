package bowling.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ScoreTest {

    @Test
    void shouldValidateScore() {
        assertThatThrownBy(() -> new Score(-1))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> new Score(11))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void shouldValidateGutter() {
        assertThat(new Score(0).isGutter()).isTrue();
        assertThat(new Score(5).isGutter()).isFalse();
    }

    @Test
    void shouldValidateStrike() {
        assertThat(new Score(10).isStrike()).isTrue();
        assertThat(new Score(5).isStrike()).isFalse();
    }

    @Test
    void shouldSumScore() {
        assertThat(new Score(5).sum(new Score(5))).isEqualTo(new Score(10));
    }

}
