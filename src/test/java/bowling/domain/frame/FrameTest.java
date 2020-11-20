package bowling.domain.frame;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FrameTest {

    @Test
    void spare() {
        Frame frame = Frame.first().last();
        frame.roll(5);
        frame.roll(5);
        frame.roll(5);

        assertThat(frame.getScore().getValue()).isEqualTo(15);
    }

    @Test
    void strike() {
        Frame frame = Frame.first().last();
        frame.roll(10);
        frame.roll(5);
        frame.roll(5);

        assertThat(frame.canRoll()).isEqualTo(false);
        assertThat(frame.getScore().getValue()).isEqualTo(20);
    }
}
