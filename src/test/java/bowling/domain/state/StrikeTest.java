package bowling.domain.state;

import bowling.domain.pin.FallenPin;
import bowling.domain.score.Score;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class StrikeTest {

    @Test
    void getFallenPins() {
        assertThat(new Strike().getFallenPins()).isEqualTo(List.of(FallenPin.of(10)));
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
