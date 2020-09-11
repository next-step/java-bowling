package bowling.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class NormalFrameTest {
    @Test
    void first() {
        assertThat(NormalFrame.first()).isNotNull();
    }

    @Test
    void next() {
        assertThat(NormalFrame.first().next()).isNotNull();
    }

    @Test
    void nextToEnd() {
        Frame frame = NormalFrame.first();
        int count = 1;

        while (!frame.isEndFrame()) {
            frame = frame.next();
            count++;
        }

        assertThat(count).isEqualTo(Frames.END_NUMBER);
    }

    @Test
    void getNumber() {
        assertThat(NormalFrame.first().getNumber()).isEqualTo(Frames.BEGIN_NUMBER);
    }

    @Test
    void hit() {
        assertThat(NormalFrame.first().hit(Frames.PIN_CLEAR_COUNT)).isEqualTo(Frames.PIN_COUNT);
        assertThat(NormalFrame.first().hit(Frames.PIN_COUNT)).isEqualTo(Frames.PIN_CLEAR_COUNT);
    }

    @Test
    void isClear() {
        NormalFrame first = NormalFrame.first();
        first.hit(Frames.PIN_COUNT);

        assertThat(first.isClear()).isTrue();
    }
}
