package bowling.domain.frame;

import bowling.domain.pins.Pins;
import bowling.domain.state.Gutter;
import bowling.domain.state.Miss;
import bowling.domain.state.Spare;
import bowling.domain.state.Strike;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FrameTest {

    Frame frame;

    @BeforeEach
    void setup() {
        frame = NormalFrame.of(1);
    }

    @DisplayName("스트라이크 ")
    @Test
    void strike() {
        frame.bowl(Pins.of(10));

        assertThat(frame.getState()).isInstanceOf(Strike.class);
        assertThat(frame.getState().toString()).hasToString("X");
    }

    @DisplayName("스페어")
    @Test
    void spare() {
        frame.bowl(Pins.of(3));
        frame.bowl(Pins.of(7));

        assertThat(frame.getState()).isInstanceOf(Spare.class);
        assertThat(frame.getState().toString()).hasToString("3|/");
    }

    @DisplayName("미스")
    @Test
    void miss() {
        frame.bowl(Pins.of(3));
        frame.bowl(Pins.of(4));

        assertThat(frame.getState()).isInstanceOf(Miss.class);
        assertThat(frame.getState().toString()).hasToString("3|4");
    }

    @DisplayName("거터")
    @Test
    void gutter() {
        frame.bowl(Pins.of(0));
        frame.bowl(Pins.of(0));

        assertThat(frame.getState()).isInstanceOf(Gutter.class);
        assertThat(frame.getState().toString()).hasToString("-");
    }
}