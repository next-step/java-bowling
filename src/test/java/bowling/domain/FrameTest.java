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
    void hit_strike() {
        Frame frame = NormalFrame.from();
        frame.hit(10);
        assertThat(frame.value()).isEqualTo(Arrays.asList("X"));
    }

    @Test
    void hit_spare() {
        Frame frame = NormalFrame.from();
        frame.hit(1);
        assertThat(frame.value()).isEqualTo(Arrays.asList("1"));
        frame.hit(9);
        assertThat(frame.value()).isEqualTo(Arrays.asList("1", "/"));
    }

    @Test
    void hit_miss() {
        Frame frame = NormalFrame.from();
        frame.hit(1);
        assertThat(frame.value()).isEqualTo(Arrays.asList("1"));
        frame.hit(8);
        assertThat(frame.value()).isEqualTo(Arrays.asList("1", "8"));
    }

    @Test
    void hit_gutter() {
        Frame frame = NormalFrame.from();
        frame.hit(0);
        assertThat(frame.value()).isEqualTo(Arrays.asList("-"));
        frame.hit(0);
        assertThat(frame.value()).isEqualTo(Arrays.asList("-", "-"));
    }

    @Test
    void next() {
        assertThat(NormalFrame.from().next().getNumber()).isEqualTo(2);
    }

    @Test
    void next_last() {
        Frame frame = NormalFrame.from();

        for (int index = 0; index < 10; index++) {
            frame = frame.hit(10);
        }

        assertThat(frame.getNumber()).isEqualTo(10);
        assertThat(frame).isInstanceOf(LastFrame.class);
    }
}
