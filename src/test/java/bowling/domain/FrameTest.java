package bowling.domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class FrameTest {

    @Test
    void from() {
        assertThat(NormalFrame.from()).isNotNull();
    }

    @Test
    void hit_one() {
        Frame frame = NormalFrame.from();

        frame.hit(1);

        assertThat(frame.isFinish()).isFalse();
        assertThat(frame.toPins()).isEqualTo(Arrays.asList(Pin.of(1)));
    }

    @Test
    void hit_strike() {
        Frame frame = NormalFrame.from();

        frame.hit(10);

        assertThat(frame.isFinish()).isTrue();
        assertThat(frame.toPins()).isEqualTo(Arrays.asList(Pin.of(10)));
    }

    @Test
    void hit_spare() {
        Frame frame = NormalFrame.from();

        frame.hit(1);
        frame.hit(9);

        assertThat(frame.isFinish()).isTrue();
        assertThat(frame.toPins()).isEqualTo(Arrays.asList(Pin.of(1), Pin.of(9)));
    }

    @Test
    void hit_miss() {
        Frame frame = NormalFrame.from();

        frame.hit(1);
        frame.hit(8);

        assertThat(frame.isFinish()).isTrue();
        assertThat(frame.toPins()).isEqualTo(Arrays.asList(Pin.of(1), Pin.of(8)));
    }

    @Test
    void hit_gutter() {
        Frame frame = NormalFrame.from();

        frame.hit(0);
        frame.hit(0);

        assertThat(frame.isFinish()).isTrue();
        assertThat(frame.toPins()).isEqualTo(Arrays.asList(Pin.of(0), Pin.of(0)));
    }
}
