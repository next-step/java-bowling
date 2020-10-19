package bowling.domain.game;

import bowling.domain.frame.Frames;
import bowling.domain.pin.Pin;
import bowling.domain.player.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class GameTest {

    private Player player;
    private Game game;
    private Frames frames;

    @BeforeEach
    public void setUp() {
        player = new Player("TDD");
        game = new Game(player);
        frames = game.getFrames();
    }

    @Test
    void firstFrameTest() {
        assertThat(game.getFirstFrame()).isSameAs(frames.getFrame(0));
    }

    @Test
    void secondFrameTest() {
        assertThat(
                game.roll(new Pin(10))
        ).isSameAs(frames.getFrame(1));
    }
}
