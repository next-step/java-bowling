package bowling.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    @Test
    void testFrame() {
        Game game = new Game();
        game.startGame();

        assertThat(game.getFrames().size()).isEqualTo(10);
    }


}
