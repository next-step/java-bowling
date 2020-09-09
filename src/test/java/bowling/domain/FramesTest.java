package bowling.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class FramesTest {
    @Test
    void create() {
        assertThat(Frames.create()).isNotNull();
    }

    @Test
    void fromThrowException() {
        assertThatThrownBy(() -> Frames.from(Frames.COUNT + 1))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> Frames.from(Frames.COUNT - 1))
                .isInstanceOf(IllegalArgumentException.class);
    }


    @Test
    void isPlayingWithNoCount() {
        Frames frames = Frames.create();
        int tryCount = 0;

        while (frames.isPlaying()) {
            frames.record(GameResult.NO_COUNT);
            tryCount++;
        }

        assertThat(tryCount).isEqualTo(20);
    }

    @Test
    void isPlayingWithAllStrike() {
        Frames frames = Frames.create();
        int tryCount = 0;

        while (frames.isPlaying()) {
            frames.record(GameResult.STRIKE_COUNT);
            tryCount++;
        }

        assertThat(tryCount).isEqualTo(11);
    }
}
