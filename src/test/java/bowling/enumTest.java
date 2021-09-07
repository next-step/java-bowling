package bowling;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class enumTest {

    @Test
    void isStrike() {

        assertThat(ScoreRule.of(10, true)).isEqualTo(ScoreRule.STRIKE);

    }

    @Test
    void isSpare() {

        assertThat(ScoreRule.of(10, false)).isEqualTo(ScoreRule.SPARE);

    }

    @Test
    void isGutter() {

        assertThat(ScoreRule.of(0, true)).isEqualTo(ScoreRule.GUTTER);

    }

    @Test
    void isMiss() {

        assertThat(ScoreRule.of(5, true)).isEqualTo(ScoreRule.MISS);

    }
}
