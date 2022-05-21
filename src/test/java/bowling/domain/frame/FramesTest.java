package bowling.domain.frame;

import bowling.domain.pin.Pin;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class FramesTest {

    @Test
    void bowl_WhenFinished_ThrowsIllegalStateException() {
        Frames frames = createFullFrames();

        assertThatThrownBy(() -> frames.bowl(Pin.of(1)))
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    void currentFrame() {
        Frames frames = new Frames();

        frames.bowl(Pin.of(4));
        frames.bowl(Pin.of(5));

        assertThat(frames.currentFrame()).isEqualTo(2);
    }

    @Test
    void scores() {
        Frames frames = new Frames();

        frames.bowl(Pin.of(4));
        frames.bowl(Pin.of(5));

        frames.bowl(Pin.of(5));
        frames.bowl(Pin.of(5));

        frames.bowl(Pin.of(5));

        assertThat(frames.scores()).containsExactly(9, 15);
    }

    @Test
    void expressions() {
        Frames frames = new Frames();

        frames.bowl(Pin.of(4));
        frames.bowl(Pin.of(5));

        frames.bowl(Pin.of(5));
        frames.bowl(Pin.of(5));

        frames.bowl(Pin.of(5));

        assertThat(frames.expressions()).containsExactly("4|5", "5|/", "5");
    }

    private Frames createFullFrames() {
        Frames frames = new Frames();
        for (int i = 0; i < 10; i++) {
            frames.bowl(Pin.of(4));
            frames.bowl(Pin.of(5));
        }
        return frames;
    }
}
