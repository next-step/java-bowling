package bowling.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class LastFrameTest {
    @Test
    void from() {
        assertThat(LastFrame.from(Frames.END_NUMBER)).isNotNull();
    }

    @Test
    void getNumber() {
        assertThat(LastFrame.from(Frames.END_NUMBER).getNumber()).isEqualTo(Frames.END_NUMBER);
    }

    @Test
    void hit() {
        assertThat(LastFrame.from(1).hit(Frames.PIN_CLEAR_COUNT)).isEqualTo(Frames.PIN_COUNT);
    }

    @Test
    void isClear() {
        Frame first = LastFrame.from(1);
        first.hit(Frames.PIN_COUNT);

        assertThat(first.isClear()).isTrue();
    }
}
