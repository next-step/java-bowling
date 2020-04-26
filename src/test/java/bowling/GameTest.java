package bowling;

import org.junit.Ignore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class GameTest {

    private Game game;

    @BeforeEach
    public void setUp() {
        game = new Game();
    }

    @Test
    public void canRoll() {
        game.roll(0);
    }

    @Test
    public void gutterGame() {
        rollMany(0, 20);
        assertThat(game.getScore()).isEqualTo(0);
    }

    @Test
    public void allOnes() {
        rollMany(1, 20);
        assertThat(game.getScore()).isEqualTo(20);
    }

    /**
     * oneSpare game : 1게임 스페어, 2게임:3, 나머지 게임은 거터 게임
     * (5 + 5 + 3) + 3 = 16
     */
    @Test
    public void oneSpare() {
        rollSpare();
        game.roll(3);
        rollMany(17,0);
        assertThat(game.getScore()).isEqualTo(16);
    }

    @Test
    public void oneStrike() {
        game.roll(10);
        game.roll(5);
        game.roll(3);
        rollMany(16, 0);
        assertThat(game.getScore()).isEqualTo(26);
    }

    private void rollSpare() {
        game.roll(5);
        game.roll(5);
    }

    private void rollMany(int pins, int frames) {
        for (int i = 0; i < frames; i++) {
            game.roll(pins);
        }
    }

}
