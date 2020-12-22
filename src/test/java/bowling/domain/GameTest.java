package bowling.domain;

import bowling.domain.frame.Frame;
import bowling.domain.frame.NormalFrame;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import static bowling.domain.state.Pins.MAX_PINS;
import static org.assertj.core.api.Assertions.assertThat;

class GameTest {

    private Game game;
    private List<GameOfPlayer> gameOfPlayers;
    private Frame frame;

    @BeforeEach
    void setUp() {
        game = new Game(Arrays.asList("pob", "sea"));
        gameOfPlayers = game.getGameOfPlayers();
        frame = new NormalFrame(0);
    }

    @Test
    @DisplayName("게임 종료")
    void isGameEnd_true() {
        IntStream.range(0, 12)
            .forEach(i -> gameOfPlayers.forEach(player -> player.playFrame(MAX_PINS, frame)));

        assertThat(game.isGameEnd()).isTrue();
    }

    @Test
    @DisplayName("게임 종료x - 한명 게임 종료x")
    void isGameEnd_false() {
        IntStream.range(0, 11)
            .forEach(i -> gameOfPlayers.forEach(player -> player.playFrame(MAX_PINS, frame)));
        gameOfPlayers.get(0).playFrame(MAX_PINS, frame);

        assertThat(game.isGameEnd()).isFalse();
    }
}