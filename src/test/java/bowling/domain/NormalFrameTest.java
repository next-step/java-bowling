package bowling.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class NormalFrameTest {
    @Test
    void from() {
        assertThat(NormalFrame.from(1)).isNotNull();
    }

    @Test
    void getNumber() {
        assertThat(NormalFrame.from(1).getNumber()).isEqualTo(Frames.BEGIN_NUMBER);
    }

    @Test
    void hit() {
        assertThat(NormalFrame.from(1).hit(Frames.PIN_CLEAR_COUNT)).isEqualTo(Frames.PIN_COUNT);
        assertThat(NormalFrame.from(1).hit(Frames.PIN_COUNT)).isEqualTo(Frames.PIN_CLEAR_COUNT);
    }

    @Test
    void isClear() {
        Frame first = NormalFrame.from(1);
        first.hit(Frames.PIN_COUNT);

        assertThat(first.isClear()).isTrue();
    }
}
