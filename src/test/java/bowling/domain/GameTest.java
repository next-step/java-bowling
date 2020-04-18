package bowling.domain;

import bowling.controller.dto.FrameStatus;
import bowling.controller.dto.GameStatus;
import bowling.domain.pitch.Pitch;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class GameTest {
    private Game game;
    private Game finishedGame;

    @BeforeEach
    void setUp() {
        String name = "BRK";
        game = new Game(name);

        finishedGame = (new Game(name));
        for (int i = 0; i < Frames.DEFAULT_FRAME_SIZE; i++) {
            finishedGame.addPin(10);
        }
        finishedGame.addPin(2);
        finishedGame.addPin(3);
    }

    @DisplayName("게임이 끝났는지 확인한다")
    @Test
    void isFinished() {
        assertThat(finishedGame.isFinished()).isTrue();
    }

    @DisplayName("쓰러트린 핀 갯수를 추가한다")
    @Test
    void addPinCount() {
        assertThat(game.addPin(1)).isTrue();
    }

    @DisplayName("현재 프레임 정보를 얻어온다")
    @Test
    void getCurrentFrame() {
        game.addPin(10);

        assertThat(game.getCurrentFrame()).isEqualTo(2);
    }

    @DisplayName("핀 갯수 저장 후 현재 프레임이 끝났다면, 다음 프레임으로 넘어간다")
    @Test
    void nextFrame() {
        game.addPin(10);
        assertThat(game.getCurrentFrame()).isEqualTo(2);
    }
}
