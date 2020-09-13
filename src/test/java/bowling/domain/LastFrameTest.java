package bowling.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LastFrameTest {

    @Test
    void last() {
        assertThat(LastFrame.from()).isNotNull();
    }

    @Test
    void hit_strike() {
        Frame frame = LastFrame.from();
        assertThat(frame.hit(10)).isEqualTo("X");
        assertThat(frame.hit(10)).isEqualTo("X|X");
        assertThat(frame.hit(10)).isEqualTo("X|X|X");
    }

    @Test
    void hit_spare() {
        Frame frame = LastFrame.from();
        assertThat(frame.hit(1)).isEqualTo("1");
        assertThat(frame.hit(9)).isEqualTo("1|/");
        assertThat(frame.hit(10)).isEqualTo("1|/|X");
    }

    @Test
    void hit_miss() {
        Frame frame = LastFrame.from();
        assertThat(frame.hit(1)).isEqualTo("1");
        assertThat(frame.hit(8)).isEqualTo("1|8");
    }

    @Test
    void hit_gutter() {
        Frame frame = LastFrame.from();
        assertThat(frame.hit(0)).isEqualTo("-");
        assertThat(frame.hit(0)).isEqualTo("-|-");
    }

    @Test
    void hit_exception() {
        assertThatThrownBy(() -> {
            Frame frame = LastFrame.from();
            frame.hit(0);
            frame.hit(0);
            frame.hit(0);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void hit_spare_exception() {
        assertThatThrownBy(() -> {
            Frame frame = LastFrame.from();
            frame.hit(1);
            frame.hit(9);
            frame.hit(0);
            frame.hit(0);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void hit_strike_exception() {
        assertThatThrownBy(() -> {
            Frame frame = LastFrame.from();
            frame.hit(10);
            frame.hit(0);
            frame.hit(0);
            frame.hit(0);
        }).isInstanceOf(IllegalArgumentException.class);
    }
}
