package bowling.domain;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.assertj.core.api.Assertions.*;

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

            frame.play(3)
                    .play(3)
                    .play(3);
        });
    }

    @Test
    public void invalid_스트라이크() {
        assertThatIllegalArgumentException().isThrownBy(() -> {
            final Frame frame = NormalFrame.init();

            frame.play(10)
                    .play(3);
        });
    }

    @Test
    public void play_일반() {
        Frame frame = NormalFrame.init();
        assertThat(frame.playing()).isTrue();

        frame = frame.play(7);
        assertThat(frame.playing()).isTrue();

        frame = frame.play(2);
        assertThat(frame.playing()).isFalse();
    }

    @Test
    public void play_스트라이크() {
        Frame frame = NormalFrame.init();
        assertThat(frame.playing()).isTrue();

        frame = frame.play(10);
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

        assertThat(last).isInstanceOf(FinalFrame.class);
    }

    @Test
    public void score_open() {
        Frame frame = NormalFrame.init();
        frame = frame.play(7);
        frame = frame.play(2);

        assertThat(frame.score(Collections.singletonList(frame))).isEqualTo(new Score(9, 0));
    }

    @Test
    public void score_spare() {
        Frame frame = NormalFrame.init();
        frame = frame.play(7);
        frame = frame.play(3);

        Frame next = frame.next();
        next = next.play(7);

        assertThat(frame.score(Arrays.asList(frame, next))).isEqualTo(new Score(17, 0));
    }

    @Test
    public void score_strike() {
        Frame frame = NormalFrame.init();
        frame = frame.play(10);

        Frame next1 = frame.next();
        next1 = next1.play(10);

        Frame next2 = next1.next();
        next2 = next2.play(10);

        assertThat(frame.score(Arrays.asList(frame, next1, next2))).isEqualTo(new Score(30, 0));
    }
}
