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
    void hitWithAllStrike() {
        Frames frames = Frames.from();

        int tryCount = 0;

        while (!frames.isFinish()) {
            frames.hit(Frames.PIN_COUNT);
            tryCount++;
        }

        assertThat(tryCount).isEqualTo(11);
    }

    @Test
    void hitWithLastSpare() {
        Frames frames = Frames.from();

        int tryCount = 0;

        while (!frames.isFinish()) {
            tryCount++;

            if(tryCount == 10) {
                frames.hit(Frames.PIN_COUNT - 1);
            } else if(tryCount == 11) {
                frames.hit( 1);
            } else {
                frames.hit( 10);
            }
        }

        assertThat(tryCount).isEqualTo(12);
    }

    @Test
    void getNumber() {
        Frames frames = Frames.from();
        assertThat(frames.getNumber()).isEqualTo(1);
        frames.hit(Frames.PIN_COUNT);
        assertThat(frames.getNumber()).isEqualTo(2);
    }
}
