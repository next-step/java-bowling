package bowling.domain.frame;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class PlayerFramesTest {

    @Test
    void create() {
        PlayerFrames playerFrames = PlayerFrames.create();

        assertThat(playerFrames.isFinished()).isFalse();
        playerFrames.roll(10);
        assertThat(playerFrames.getCurrentPosition()).isEqualTo(1);
    }
}