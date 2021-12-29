package bowling.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class FramesTest {

    @Test
    void create() {
        assertThat(Frames.init()).isInstanceOf(Frames.class);
    }

    @Test
    void perfectBowlingGame() {
        Frames frames = Frames.init();
        frames = frames.bowl(Pins.of(10));
        frames = frames.bowl(Pins.of(10));
        frames = frames.bowl(Pins.of(10));
        frames = frames.bowl(Pins.of(10));
        frames = frames.bowl(Pins.of(10));
        frames = frames.bowl(Pins.of(10));
        frames = frames.bowl(Pins.of(10));
        frames = frames.bowl(Pins.of(10));
        frames = frames.bowl(Pins.of(10));
        frames = frames.bowl(Pins.of(10));
        frames = frames.bowl(Pins.of(10));
        frames = frames.bowl(Pins.of(10));
        assertThat(frames.isFinished()).isTrue();
    }

}