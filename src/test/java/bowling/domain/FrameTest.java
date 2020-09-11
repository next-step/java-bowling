package bowling.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FrameTest {
    @Test
    void first() {
        assertThat(Frame.first()).isNotNull();
    }

    @Test
    void next() {
        assertThat(Frame.first().next()).isNotNull();
    }

    @Test
    void nextToEnd() {
        Frame frame = Frame.first();
        int count = 1;

        while (!frame.isEndFrame()) {
            frame = frame.next();
            count++;
        }

        assertThat(count).isEqualTo(Frame.END_NUMBER);
    }

    @Test
    void getNumber() {
        assertThat(Frame.first().getNumber()).isEqualTo(Frame.BEGIN_NUMBER);
    }

    @Test
    void hit() {
        assertThat(Frame.first().hit(Frame.PIN_CLEAR_COUNT)).isEqualTo(Frame.PIN_COUNT);
        assertThat(Frame.first().hit(Frame.PIN_COUNT)).isEqualTo(Frame.PIN_CLEAR_COUNT);
    }

    @Test
    void isClear() {
        Frame first = Frame.first();
        first.hit(Frame.PIN_COUNT);

        assertThat(first.isClear()).isTrue();
    }
}
