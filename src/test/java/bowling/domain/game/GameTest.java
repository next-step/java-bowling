package bowling.domain.game;

import org.junit.jupiter.api.Test;

import static bowling.domain.frame.FramesTest.INITIAL_FRAMES;
import static bowling.domain.player.PlayerTest.PLAYER_ONE;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class GameTest {
    @Test
    void Game은_Frames없이_생성_될_경우_예외를_발생_시킨다() {
        assertThatThrownBy(() -> {
            new Game(PLAYER_ONE, null);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void Game은_Player없이_생성_될_경우_예외를_발생_시킨다() {
        assertThatThrownBy(() -> {
            new Game(null, INITIAL_FRAMES);
        }).isInstanceOf(IllegalArgumentException.class);
    }
}
