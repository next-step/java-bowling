package bowling.domain.frame;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

class FrameTest {

    @Test
    void strike_and_play_then_throw_exception() {
        Frame normalFrame = Frame.first();

        normalFrame.roll(10);
        assertThatThrownBy(() -> normalFrame.roll(1))
            .isInstanceOf(IllegalStateException.class);
    }

    @Test
    void third_play_then_exception() {
        Frame normalFrame = Frame.first();

        normalFrame.roll(8);
        normalFrame.roll(1);

        assertThatThrownBy(() -> normalFrame.roll(1))
            .isInstanceOf(IllegalStateException.class);
    }
}
