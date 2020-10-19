package bowling.domain.game;

import bowling.domain.player.Player;
import org.junit.jupiter.api.BeforeEach;

class GameTest {

    private Player player;
    private Game game;

    @BeforeEach
    public void setUp() {
        player = new Player("TDD");
        game = new Game(player);
    }
}
