package bowling.domain.game;

import bowling.domain.frame.FrameNumber;
import bowling.domain.frame.Frames;
import bowling.domain.state.PinTest;
import org.junit.jupiter.api.Test;

import static bowling.domain.frame.FramesTest.INITIAL_FRAMES;
import static bowling.domain.player.PlayerTest.PLAYER_ONE;
import static org.assertj.core.api.Assertions.assertThat;
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

    @Test
    void bowl은_현재_프레임에_점수를_추가하고_프레임을_추가_시킨다() {
        Game game = new Game(PLAYER_ONE, Frames.initialize());

        game.bowl(PinTest.TEN);

        assertThat(game.currentFrameNumber()).isEqualTo(new FrameNumber(2));
    }
}
