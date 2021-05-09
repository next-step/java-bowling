package bowling.domain;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class NormalFrameTest {
    @Test
    public void init() {
        final Frame frame = NormalFrame.init();
        assertThat(frame.playing()).isTrue();
    }

    @Test
    public void invalid_투구_2개_초과() {
        assertThatIllegalArgumentException().isThrownBy(() -> {
            final Frame frame = NormalFrame.init();

            frame.play(KnockedPins.from(3))
                    .play(KnockedPins.from(3))
                    .play(KnockedPins.from(3));
        });
    }

    @Test
    public void invalid_스트라이크() {
        assertThatIllegalArgumentException().isThrownBy(() -> {
            final Frame frame = NormalFrame.init();

            frame.play(KnockedPins.from(10))
                    .play(KnockedPins.from(3));
        });
    }

    @Test
    public void play_일반() {
        Frame frame = NormalFrame.init();
        assertThat(frame.playing()).isTrue();

        frame = frame.play(KnockedPins.from(7));
        assertThat(frame.playing()).isTrue();

        frame = frame.play(KnockedPins.from(2));
        assertThat(frame.playing()).isFalse();
    }

    @Test
    public void play_스트라이크() {
        Frame frame = NormalFrame.init();
        assertThat(frame.playing()).isTrue();

        frame = frame.play(KnockedPins.from(10));
        assertThat(frame.playing()).isFalse();
    }

    @Test
    public void next() {
        final Frame next = NormalFrame.init().next();

        assertThat(next).isInstanceOf(NormalFrame.class);
    }

    @Test
    public void last() {
        final Frame last = NormalFrame.init().last();

        assertThat(last).isInstanceOf(LastFrame.class);
    }
}
