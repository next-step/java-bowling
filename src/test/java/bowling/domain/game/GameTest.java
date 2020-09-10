package bowling.domain.game;

import bowling.domain.player.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class GameTest {
    private static final int MAX_FRAME = 10;
    private static final int STRIKE = 10;

    private Player player;
    private Game game;

    @BeforeEach
    public void setUp() {
        player = new Player("TDD");
        game = new Game(player);
    }

    @Test
    void testFrame() {
        assertThat(game.getFrames().size()).isEqualTo(MAX_FRAME);
    }

    @Test
    @DisplayName("다음 프레임 가져오기 테스트")
    void testNextFrame() {
        rolledThird();

        assertThat(game.getNextFrame().getIndex()).isEqualTo(4);
    }

    private void rolledThird() {
        game.getNextFrame().roll(STRIKE);
        game.getNextFrame().roll(STRIKE);
        game.getNextFrame().roll(STRIKE);
    }

}
