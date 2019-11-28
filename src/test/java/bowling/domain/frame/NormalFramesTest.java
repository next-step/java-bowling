package bowling.domain.frame;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class NormalFramesTest {

    @Test
    @DisplayName("점수를 계산한다.")
    void calculateScore() {

        NormalFrames frames = new NormalFrames();

        frames.bowl(7);
        frames.bowl(1);
        frames.bowl(10);
        assertThat(frames.getNormalFrames().get(0).getScore()).isEqualTo(8);
    }
    @Test
    @DisplayName("점수를 계산한다.")
    void calculateScoreStrike() {

        NormalFrames frames = new NormalFrames();

        frames.bowl(10);
        frames.bowl(8);
        frames.bowl(1);
        assertThat(frames.getNormalFrames().get(0).getScore()).isEqualTo(19);
    }

    @Test
    @DisplayName("점수를 계산한다.")
    void calculateScoreSpare() {

        NormalFrames frames = new NormalFrames();

        frames.bowl(8);
        frames.bowl(2);
        frames.bowl(1);
        assertThat(frames.getNormalFrames().get(0).getScore()).isEqualTo(11);
    }
}
