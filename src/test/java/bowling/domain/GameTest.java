package bowling.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class GameTest {
    private String player1;
    private String player2;
    private Game game;
    private Game finishedGame;

    @BeforeEach
    void setUp() {
        player1 = "BRK";
        player2 = "LVR";
        List<String> playerNames = Arrays.asList(player1, player2);
        game = new Game(playerNames);

        finishedGame = (new Game(Collections.singletonList("BRK")));
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

    @DisplayName("현재 플레이어 정보를 얻어온다")
    @Test
    void getCurrentFrame() {
        game.addPin(10);
        assertThat(game.getCurrentPlayerName()).isEqualTo(player2);
    }
}
