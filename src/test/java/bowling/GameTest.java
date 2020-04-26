package bowling;

import org.junit.Before;
import org.junit.jupiter.api.Test;

public class GameTest {

    private Game game;

    @Before
    public void setUp() {
        game = new Game();
    }

    @Test
    public void canRoll() {
        game = new Game();
        game.roll(0);
    }
}
