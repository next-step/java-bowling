package bowling.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import org.junit.jupiter.api.Test;

class FinalFrameTest {

    @Test
    void create() {
        assertThat(new FinalFrame()).isInstanceOf(FinalFrame.class);
    }

    @Test
    void 스트라이크시3번가능() {
        Frame finalFrame = new FinalFrame();
        finalFrame = finalFrame.addScore(Pins.of(10));
        finalFrame = finalFrame.addScore(Pins.of(10));
        assertThat(finalFrame.addScore(Pins.of(10))).isInstanceOf(FinalFrame.class);
    }

    @Test
    void 스페어일경우3번가능() {
        Frame finalFrame = new FinalFrame();
        finalFrame = finalFrame.addScore(Pins.of(0));
        finalFrame = finalFrame.addScore(Pins.of(10));
        assertThat(finalFrame.addScore(Pins.of(10))).isInstanceOf(FinalFrame.class);
    }

    @Test
    void 스트라이크_스페어_둘다아닐경우_2번만가능() {
        Frame finalFrame = new FinalFrame();
        finalFrame.addScore(Pins.of(0));
        finalFrame.addScore(Pins.of(2));
        assertThatExceptionOfType(IllegalArgumentException.class)
            .isThrownBy(() -> finalFrame.addScore(Pins.of(4)));
    }

    @Test
    void 횟수가4번이상은불가능() {
        Frame finalFrame = new FinalFrame();
        finalFrame.addScore(Pins.of(10));
        finalFrame.addScore(Pins.of(10));
        finalFrame.addScore(Pins.of(10));
        assertThatExceptionOfType(IllegalArgumentException.class)
            .isThrownBy(() -> finalFrame.addScore(Pins.of(10)));
    }

}