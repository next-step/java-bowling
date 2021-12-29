package bowling.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class FramesTest {

    @Test
    void 생성() {
        assertThat(Frames.init()).isInstanceOf(Frames.class);
    }

    @Test
    void 퍼펙트_볼링_게임() {
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