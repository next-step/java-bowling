package bowling.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ScoreTest {
    @Test
    void miss() {
        assertThat(new Score(0, null, null).toInt()).isNull();
        assertThat(new Score(8, 1, null).toInt()).isEqualTo(9);
    }

    @Test
    void strike() {
        assertThat(new Score(10, null, null).toInt()).isNull();
        assertThat(new Score(10, 8, null).toInt()).isNull();
        assertThat(new Score(10, 8, 1).toInt()).isEqualTo(19);
    }

    @Test
    void spare() {
        assertThat(new Score(8, 2, null).toInt()).isNull();
        assertThat(new Score(8, 2, 1).toInt()).isEqualTo(11);
    }

}
