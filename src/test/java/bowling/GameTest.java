package bowling;

import org.junit.jupiter.api.Test;

public class GameTest {

    @Test
    public void canCreate() {
        Game game = new Game();
    }

    @Test
    public void canRoll() {
        Game game = new Game();
        game.roll(0);
    }
}
