package bowling.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FrameTest {

    @Test
    void from() {
        assertThat(NormalFrame.from()).isNotNull();
    }

    @Test
    void hit_strike() {
        Frame frame = NormalFrame.from();
        assertThat(frame.hit(10).getNumber()).isEqualTo(2);
    }

    @Test
    void hit_spare() {
        Frame frame = NormalFrame.from();
        assertThat(frame.hit(1).getNumber()).isEqualTo(1);
        assertThat(frame.hit(9).getNumber()).isEqualTo(2);
    }

    @Test
    void hit_miss() {
        Frame frame = NormalFrame.from();
        assertThat(frame.hit(1).getNumber()).isEqualTo(1);
        assertThat(frame.hit(8).getNumber()).isEqualTo(2);
    }

    @Test
    void hit_gutter() {
        Frame frame = NormalFrame.from();
        assertThat(frame.hit(0).getNumber()).isEqualTo(1);
        assertThat(frame.hit(0).getNumber()).isEqualTo(2);
    }

    @Test
    void next() {
        assertThat(NormalFrame.from().next().getNumber()).isEqualTo(2);
    }

    @Test
    void next_last() {
        Frame frame = NormalFrame.from();

        for (int index = 0; index < 9; index++) {
            frame = frame.hit(10);
        }

        assertThat(frame.getNumber()).isEqualTo(10);
        assertThat(frame).isInstanceOf(LastFrame.class);
    }

    @Test
    void canGoNext() {
        Frame frame = NormalFrame.from();

        assertThat(frame.canGoNextFrame()).isFalse();
        frame.hit(10);

        assertThat(frame.canGoNextFrame()).isTrue();
    }

    @Test
    void canGoNext_last() {
        Frame frame = LastFrame.from();

        assertThat(frame.canGoNextFrame()).isFalse();
        frame.hit(10);
        frame.hit(10);
        frame.hit(10);

        assertThat(frame.canGoNextFrame()).isFalse();
    }
}
