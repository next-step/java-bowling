package bowling.domain.state;

import bowling.domain.pin.FallenPin;
import bowling.domain.score.Score;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SpareTest {

    @Test
    void description() {
        assertThat(spare().description()).isEqualTo("9|/");
    }

    @Test
    void tries() {
        assertThat(spare().tries()).isEqualTo(2);
    }

    @Test
    void getScore() {
        FrameState frameState = spare();

        assertThat(frameState.getScore()).isEqualTo(new Score(10, 1));
    }

    @Test
    void addScore() {
        FrameState frameState = spare();
        Score previousScore = new Score(10, 1);

        assertThat(frameState.addScore(previousScore)).isEqualTo(new Score(19, 0));
    }

    private Spare spare() {
        return new Spare(FallenPin.of(9), FallenPin.of(1));
    }
}
