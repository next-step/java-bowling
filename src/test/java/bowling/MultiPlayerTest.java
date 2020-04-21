package bowling;

import bowling.domain.Game;
import bowling.domain.Player;
import bowling.domain.Score;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class MultiPlayerTest {
    private static Game game;
    private static Player player;

    @BeforeEach
    void setUp() {
        game = new Game();
        player = new Player("kim");
    }

    @Test
    public void multiPlayerTest() {
        game.add(player);
        assertThat(game.getPlay(0).getName()).isEqualTo("kim");
    }

    @Test
    public void isEndGameTest() {
        game.add(player);
        game.play(new Score(10), 0);
        game.play(new Score(10), 0);
        game.play(new Score(10), 0);
        game.play(new Score(10), 0);
        game.play(new Score(10), 0);
        game.play(new Score(10), 0);
        game.play(new Score(10), 0);
        game.play(new Score(10), 0);
        game.play(new Score(10), 0);
        game.play(new Score(10), 0);
        game.play(new Score(10), 0);
        game.play(new Score(10), 0);
        assertThat(game.isEndGame()).isTrue();
    }
}
