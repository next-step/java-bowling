package bowling.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FramesTest {

    @Test
    void from() {
        assertThat(Frames.from()).isNotNull();
    }

    @Test
    void hit() {
        Frames frames = Frames.from();

        int tryCount = 0;

        while (!frames.isFinish()) {
            frames.hit(Frames.PIN_CLEAR_COUNT);
            tryCount++;
        }

        assertThat(tryCount).isEqualTo(20);
    }

    @Test
    void nextWithAllStrike() {
        Frames frames = Frames.from();

        int tryCount = 0;

        while (!frames.isFinish()) {
            frames.hit(Frames.PIN_COUNT);
            tryCount++;
        }

        assertThat(tryCount).isEqualTo(11);
    }

    @Test
    void getNumber() {
        Frames frames = Frames.from();
        assertThat(frames.getNumber()).isEqualTo(1);
        assertThat(frames.hit(Frames.PIN_COUNT).getNumber()).isEqualTo(2);
    }
}
