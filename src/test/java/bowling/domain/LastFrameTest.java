package bowling.domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

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
        assertThat(frame.toPins()).isEqualTo(Arrays.asList(Pin.of(10)));
        assertThat(frame.getScore().toInt()).isEqualTo(10);
    }

    @Test
    void double_strike() {
        Frame frame = LastFrame.from();

        frame = frame.hit(10);
        frame = frame.hit(10);

        assertThat(frame.isFinish()).isFalse();
        assertThat(frame.toPins()).isEqualTo(Arrays.asList(Pin.of(10), Pin.of(10)));
        assertThat(frame.getScore().toInt()).isEqualTo(20);
    }

    @Test
    void open() {
        Frame frame = LastFrame.from().hit(1).hit(8);

        assertThat(frame.isFinish()).isTrue();
        assertThat(frame.toPins()).isEqualTo(Arrays.asList(Pin.of(1), Pin.of(8)));
        assertThat(frame.getScore().toInt()).isEqualTo(9);
    }

    @Test
    void triple() {
        Frame frame = LastFrame.from().hit(10).hit(10).hit(10);

        assertThat(frame.isFinish()).isTrue();
        assertThat(frame.toPins()).isEqualTo(Arrays.asList(Pin.of(10), Pin.of(10), Pin.of(10)));
        assertThat(frame.getScore().toInt()).isEqualTo(30);
    }


    @Test
    void double_open() {
        Frame frame = LastFrame.from();

        frame.hit(10);
        frame.hit(10);
        frame.hit(9);

        assertThat(frame.isFinish()).isTrue();
        assertThat(frame.toPins()).isEqualTo(Arrays.asList(Pin.of(10), Pin.of(10), Pin.of(9)));
        assertThat(frame.getScore().toInt()).isEqualTo(29);
    }

    @Test
    void double_gutter() {
        Frame frame = LastFrame.from();

        frame.hit(10);
        frame.hit(10);
        frame.hit(0);

        assertThat(frame.isFinish()).isTrue();
        assertThat(frame.toPins()).isEqualTo(Arrays.asList(Pin.of(10), Pin.of(10), Pin.of(0)));
        assertThat(frame.getScore().toInt()).isEqualTo(20);
    }

    @Test
    void strike_spare() {
        Frame frame = LastFrame.from();

        frame.hit(10);
        frame.hit(8);
        frame.hit(2);

        assertThat(frame.isFinish()).isTrue();
        assertThat(frame.toPins()).isEqualTo(Arrays.asList(Pin.of(10), Pin.of(8), Pin.of(2)));
        assertThat(frame.getScore().toInt()).isEqualTo(20);
    }

    @Test
    void strike_open() {
        Frame frame = LastFrame.from();

        frame.hit(10);
        frame.hit(8);
        frame.hit(1);

        assertThat(frame.isFinish()).isTrue();
        assertThat(frame.toPins()).isEqualTo(Arrays.asList(Pin.of(10), Pin.of(8), Pin.of(1)));
        assertThat(frame.getScore().toInt()).isEqualTo(19);
    }

    @Test
    void strike_gutter() {
        Frame frame = LastFrame.from();

        frame.hit(10);
        frame.hit(8);
        frame.hit(0);

        assertThat(frame.isFinish()).isTrue();
        assertThat(frame.toPins()).isEqualTo(Arrays.asList(Pin.of(10), Pin.of(8), Pin.of(0)));
        assertThat(frame.getScore().toInt()).isEqualTo(18);
    }

    @Test
    void spare_strike() {
        Frame frame = LastFrame.from();

        frame.hit(8);
        frame.hit(2);
        frame.hit(10);

        assertThat(frame.isFinish()).isTrue();
        assertThat(frame.toPins()).isEqualTo(Arrays.asList(Pin.of(8), Pin.of(2), Pin.of(10)));
        assertThat(frame.getScore().toInt()).isEqualTo(20);
    }

    @Test
    void spare_open() {
        Frame frame = LastFrame.from();

        frame.hit(8);
        frame.hit(2);
        frame.hit(8);

        assertThat(frame.isFinish()).isTrue();
        assertThat(frame.toPins()).isEqualTo(Arrays.asList(Pin.of(8), Pin.of(2), Pin.of(8)));
        assertThat(frame.getScore().toInt()).isEqualTo(18);
    }

    @Test
    void spare_gutter() {
        Frame frame = LastFrame.from();

        frame.hit(8);
        frame.hit(2);
        frame.hit(0);

        assertThat(frame.isFinish()).isTrue();
        assertThat(frame.toPins()).isEqualTo(Arrays.asList(Pin.of(8), Pin.of(2), Pin.of(0)));
        assertThat(frame.getScore().toInt()).isEqualTo(10);
    }
}
