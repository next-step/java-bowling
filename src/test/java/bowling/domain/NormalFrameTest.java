package bowling.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import org.junit.jupiter.api.Test;


public class NormalFrameTest {

    @Test
    void create() {
        assertThat(new NormalFrame()).isInstanceOf(NormalFrame.class);
    }

    @Test
    void isNextScore() {
        NormalFrame normalFrame = new NormalFrame();
        normalFrame = normalFrame.addScore(Pins.of(9));
        assertThat(normalFrame.isStrike()).isFalse();
    }

    @Test
    void 스트라이크확인() {
        NormalFrame normalFrame = new NormalFrame();
        normalFrame.addScore(Pins.of(10));
        assertThat(normalFrame.isStrike()).isTrue();
    }

    @Test
    void 합이10초과일경우() {
        NormalFrame normalFrame = new NormalFrame();
        normalFrame.addScore(Pins.of(9));
        assertThatExceptionOfType(IllegalArgumentException.class)
            .isThrownBy(() -> normalFrame.addScore(Pins.of(2)));
    }

    @Test
    void 이미2번한경우() {
        NormalFrame normalFrame = new NormalFrame();
        normalFrame.addScore(Pins.of(2));
        normalFrame.addScore(Pins.of(2));
        assertThatExceptionOfType(IllegalArgumentException.class)
            .isThrownBy(() -> normalFrame.addScore(Pins.of(2)));
    }

}
