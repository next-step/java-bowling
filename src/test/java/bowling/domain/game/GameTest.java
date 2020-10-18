package bowling.domain.game;

import bowling.domain.player.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class GameTest {


    private Player player;
    private Game game;

    @BeforeEach
    public void setUp() {
        player = new Player("TDD");
        game = new Game(player);
    }
}
