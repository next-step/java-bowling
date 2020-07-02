package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class GameTest {

    @Test
    void testFrame() {
        Game game = new Game();
        game.startGame();

        assertThat(game.getFrames().size()).isEqualTo(10);
    }

    @Test
    @DisplayName("다음 프레임 가져오기 테스트")
    void testNextFrame() {
        Game game = new Game();
        game.startGame();

        game.getNextFrame().roll(10);
        game.getNextFrame().roll(10);
        game.getNextFrame().roll(10);

        assertThat(game.getNextFrame().getIndex()).isEqualTo(3);
    }



}
