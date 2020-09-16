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
        assertThat(frame.isFinish()).isTrue();
        assertThat(frame.toResults()).isEqualTo(Arrays.asList("X"));
    }

    @Test
    void hit_spare() {
        Frame frame = NormalFrame.from();
        frame.hit(1);
        assertThat(frame.isFinish()).isFalse();
        assertThat(frame.toResults()).isEqualTo(Arrays.asList("1"));
        frame.hit(9);
        assertThat(frame.isFinish()).isTrue();
        assertThat(frame.toResults()).isEqualTo(Arrays.asList("1", "/"));
    }

    @Test
    void hit_miss() {
        Frame frame = NormalFrame.from();
        frame.hit(1);
        assertThat(frame.isFinish()).isFalse();
        assertThat(frame.toResults()).isEqualTo(Arrays.asList("1"));
        frame.hit(8);
        assertThat(frame.isFinish()).isTrue();
        assertThat(frame.toResults()).isEqualTo(Arrays.asList("1", "8"));
    }

    @Test
    void hit_gutter() {
        Frame frame = NormalFrame.from();
        frame.hit(0);
        assertThat(frame.isFinish()).isFalse();
        assertThat(frame.toResults()).isEqualTo(Arrays.asList("-"));
        frame.hit(0);
        assertThat(frame.isFinish()).isTrue();
        assertThat(frame.toResults()).isEqualTo(Arrays.asList("-", "-"));
    }

}
