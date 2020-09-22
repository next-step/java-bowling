package bowling.domain;

import bowling.exception.GameOverException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class NormalFrameTest {
    @Test
    void create() {
        NormalFrame normalFrame = new NormalFrame(0);

        assertThat(normalFrame).isEqualTo(new NormalFrame(0));
    }

    @Test
    void create_invalid() {
        assertThatThrownBy(() -> new NormalFrame(9))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void firstFrame() {
        NormalFrame normalFrame = NormalFrame.firstFrame();

        assertThat(normalFrame).isEqualTo(new NormalFrame(0));
    }

    @Test
    void next() {
        NormalFrame frame = NormalFrame.firstFrame();
        NormalFrame nextFrame = frame.next();

        assertThat(nextFrame).isEqualTo(new NormalFrame(1));
    }

    @Test
    void pitch() {
        NormalFrame frame = NormalFrame.firstFrame();
        frame.pitch(10);

        assertThat(frame.getPins()).containsExactly(new Pin(10));
        assertThat(frame.getScore()).containsExactly("X");
    }

    @Test
    void pitch2() {
        NormalFrame frame = NormalFrame.firstFrame();
        frame.pitch(5);
        frame.pitch(5);

        assertThat(frame.getPins()).containsExactly(new Pin(5), new Pin(5));
        assertThat(frame.getScore()).containsExactly("5", "/");
    }


    @Test
    void pitch_overCount() {
        NormalFrame frame = NormalFrame.firstFrame();
        frame.pitch(1);
        frame.pitch(2);

        assertThatThrownBy(() -> frame.pitch(3))
                .isInstanceOf(GameOverException.class);
    }

    @Test
    void pitch_overPin() {
        NormalFrame frame = NormalFrame.firstFrame();
        frame.pitch(10);

        assertThatThrownBy(() -> frame.pitch(2))
                .isInstanceOf(GameOverException.class);
    }
}
