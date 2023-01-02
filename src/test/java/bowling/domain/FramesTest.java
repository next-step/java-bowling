package bowling.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class FramesTest {

    public static final int GAME_FINISHING_BOWL_TRIES = 12;

    @Test
    void 게임종료여부_종료되지않은_상태() {
        assertThat(framesProvider(1).gameFinished()).isFalse();
    }

    @Test
    void 게임종료여부_종료된_상태() {
        assertThat(framesProvider(GAME_FINISHING_BOWL_TRIES).gameFinished()).isTrue();
    }

    @Test
    void 게임진행_종료되지않은_상태() {
        Frames frames = new Frames();
        assertThat(frames.results()).hasSize(1);

        frames.bowl(new Pin(Pin.MAX_AMOUNT));
        assertThat(frames.results()).hasSize(2);
    }

    @Test
    void 게임진행_종료된_상태() {
        Frames frames = framesProvider(GAME_FINISHING_BOWL_TRIES);
        assertThatThrownBy(() -> frames.bowl(new Pin(Pin.MAX_AMOUNT)))
                .isInstanceOf(IllegalStateException.class);
    }

    private Frames framesProvider(int bowlTries) {
        Frames frames = new Frames();
        for (int i = 0; i < bowlTries; i++) {
            frames.bowl(new Pin(Pin.MAX_AMOUNT));
        }
        return frames;
    }
}
