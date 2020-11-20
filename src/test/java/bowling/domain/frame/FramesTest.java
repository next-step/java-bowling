package bowling.domain.frame;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class FramesTest {

    @Test
    void create() {
        Frames frames = Frames.of();
        assertThat(frames.getCurrentIndex()).isEqualTo(0);
    }

    @Test
    void roll() {
        Frames frames = Frames.of();
        frames.roll(5);
        frames.roll(5);
        assertThat(frames.getCurrentIndex()).isEqualTo(1);
    }
}
