package bowling;

import bowling.domain.Game;
import bowling.domain.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class MultiPlayerTest {
    private static Game game;
    private static Player player;

    @BeforeEach
    void setUp() {
        game = new Game();
    }

    @Test
    public void name() {
        player = new Player("kim");
        game.add(player);
        assertThat(game.getPlay(0).getName()).isEqualTo("kim");
    }
}
