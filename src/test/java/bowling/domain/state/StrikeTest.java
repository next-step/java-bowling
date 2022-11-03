package bowling.domain.state;

import bowling.domain.score.Score;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class StrikeTest {

    @Test
    void description() {
        assertThat(new Strike().description()).isEqualTo("X");
    }

    @Test
    void tries() {
        assertThat(new Strike().tries()).isEqualTo(1);
    }

    @Test
    void getScore() {
        FrameState frameState = new Strike();

        assertThat(frameState.getScore()).isEqualTo(new Score(10, 2));
    }

    @Test
    void addScore() {
        FrameState frameState = new Strike();
        Score previousScore = new Score(10, 1);

        assertThat(frameState.addScore(previousScore)).isEqualTo(new Score(20, 0));
    }
}
