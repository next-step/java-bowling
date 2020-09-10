package bowling.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FrameTest {
    @Test
    void first() {
        assertThat(Frame.first()).isNotNull();
    }

    @Test
    void next() {
        assertThat(Frame.first().next()).isNotNull();
    }

    @Test
    void nextToFinish() {
        Frame frame = Frame.first();
        int count = 1;

        while (!frame.isFinish()) {
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
        int NO_COUNT = 0;
        assertThat(Frame.first().hit(NO_COUNT)).isEqualTo(Frame.PIN_COUNT);
        assertThat(Frame.first().hit(Frame.PIN_COUNT)).isEqualTo(NO_COUNT);
    }
}
