package bowling.domain.state;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

class ScoreTest {

    @Test
    void miss() {
        Score score = Score.ofMiss(5);
        assertThat(score.getScore()).isEqualTo(5);
    }

    @Test
    void ofStrikeTest() {
        assertThatCode(
                () -> Score.ofStrike()
        ).doesNotThrowAnyException();
    }
}
