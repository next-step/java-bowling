package bowling.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FramesTest {

    @Test
    void create() {
        assertThat(Frames.create()).isNotNull();
    }

    @Test
    void next() {
        Frames frames = Frames.create();

        int tryCount = 0;

        while (!frames.isFinish()) {
            frames.hit(Frame.PIN_CLEAR_COUNT);
            tryCount++;
        }

        assertThat(tryCount).isEqualTo(20);
    }

    @Test
    void nextWithAllStrike() {
        Frames frames = Frames.create();

        int tryCount = 0;

        while (!frames.isFinish()) {
            frames.hit(Frame.PIN_COUNT);
            tryCount++;
        }

        assertThat(tryCount).isEqualTo(11);
    }
}
