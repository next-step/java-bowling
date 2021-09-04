package bowling;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ScoreTest {
    @Test
    void create() {
        assertThat(Score.of(10)).isEqualTo(Score.STRIKE);
        assertThat(Score.of(1,9)).isEqualTo(Score.SPARE);
        assertThat(Score.of(1)).isEqualTo(Score.MISS_1);
        assertThat(Score.of(9)).isEqualTo(Score.MISS_9);
        assertThat(Score.of(0)).isEqualTo(Score.GUTTER);
    }

    @Test
    void display() {
        assertThat(Score.STRIKE.getDisplay()).isEqualTo("x");
        assertThat(Score.SPARE.getDisplay()).isEqualTo("/");
        assertThat(Score.MISS_1.getDisplay()).isEqualTo("1");
        assertThat(Score.MISS_9.getDisplay()).isEqualTo("9");
        assertThat(Score.GUTTER.getDisplay()).isEqualTo("-");
    }
}
