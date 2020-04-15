package bowling.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameTest {

    @DisplayName("게임을 시작해 첫번째 프레임을 생성해준다.")
    @Test
    void startGame() {
        Game game = new Game("CSJ");
        NormalFrame normalFrame = game.startGame();
        game.addFrame(normalFrame);

        assertThat(game.getFrames()).hasSize(1);
        assertThat(game.getFrames().get(0)).isEqualTo(new NormalFrame(1));
    }
}
