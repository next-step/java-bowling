package bowling.domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LastFrameTest {

    @Test
    void last() {
        assertThat(LastFrame.from()).isNotNull();
    }

    @Test
    void strike() {
        Frame frame = LastFrame.from();
        frame.hit(10);

        assertThat(frame.isFinish()).isFalse();
        assertThat(frame.value()).isEqualTo(Arrays.asList("X"));
    }

    @Test
    void double_strike() {
        Frame frame = LastFrame.from();
        frame.hit(10);
        frame.hit(10);

        assertThat(frame.isFinish()).isFalse();
        assertThat(frame.value()).isEqualTo(Arrays.asList("X", "X"));
    }

    @Test
    void open() {
        Frame frame = LastFrame.from();
        frame.hit(1);
        frame.hit(8);

        assertThat(frame.isFinish()).isTrue();
        assertThat(frame.value()).isEqualTo(Arrays.asList("1", "8"));
    }

    @Test
    void triple() {
        Frame frame = LastFrame.from();
        frame.hit(10);
        frame.hit(10);
        frame.hit(10);

        assertThat(frame.isFinish()).isTrue();
        assertThat(frame.value()).isEqualTo(Arrays.asList("X", "X", "X"));
    }


    @Test
    void double_open() {
        Frame frame = LastFrame.from();
        frame.hit(10);
        frame.hit(10);
        frame.hit(9);

        assertThat(frame.isFinish()).isTrue();
        assertThat(frame.value()).isEqualTo(Arrays.asList("X", "X", "9"));
    }

    @Test
    void double_gutter() {
        Frame frame = LastFrame.from();
        frame.hit(10);
        frame.hit(10);
        frame.hit(0);

        assertThat(frame.isFinish()).isTrue();
        assertThat(frame.value()).isEqualTo(Arrays.asList("X", "X", "-"));
    }

    @Test
    void strike_spare() {
        Frame frame = LastFrame.from();
        frame.hit(10);
        frame.hit(8);
        frame.hit(2);

        assertThat(frame.isFinish()).isTrue();
        assertThat(frame.value()).isEqualTo(Arrays.asList("X", "8", "/"));
    }

    @Test
    void strike_open() {
        Frame frame = LastFrame.from();
        frame.hit(10);
        frame.hit(8);
        frame.hit(1);

        assertThat(frame.isFinish()).isTrue();
        assertThat(frame.value()).isEqualTo(Arrays.asList("X", "8", "1"));
    }

    @Test
    void strike_gutter() {
        Frame frame = LastFrame.from();
        frame.hit(10);
        frame.hit(8);
        frame.hit(0);

        assertThat(frame.isFinish()).isTrue();
        assertThat(frame.value()).isEqualTo(Arrays.asList("X", "8", "-"));
    }

    @Test
    void spare_strike() {
        Frame frame = LastFrame.from();
        frame.hit(8);
        frame.hit(2);
        frame.hit(10);

        assertThat(frame.isFinish()).isTrue();
        assertThat(frame.value()).isEqualTo(Arrays.asList("8", "/", "X"));
    }

    @Test
    void spare_open() {
        Frame frame = LastFrame.from();
        frame.hit(8);
        frame.hit(2);
        frame.hit(8);

        assertThat(frame.isFinish()).isTrue();
        assertThat(frame.value()).isEqualTo(Arrays.asList("8", "/", "8"));
    }

    @Test
    void spare_gutter() {
        Frame frame = LastFrame.from();
        frame.hit(8);
        frame.hit(2);
        frame.hit(0);

        assertThat(frame.isFinish()).isTrue();
        assertThat(frame.value()).isEqualTo(Arrays.asList("8", "/", "-"));
    }
}
