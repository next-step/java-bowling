package bowling.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.IntStream;

import static bowling.domain.state.Pins.MAX_PINS;
import static org.assertj.core.api.Assertions.assertThat;

class GameTest {

    private Game game;
    private GameOfPlayer pob;
    private GameOfPlayer sea;

    @BeforeEach
    void setUp() {
        pob = new GameOfPlayer(new Player("pob"));
        sea = new GameOfPlayer(new Player("pob"));
        game = new Game(Arrays.asList(pob, sea));
    }

    @Test
    @DisplayName("게임 종료")
    void isGameEnd_true() {
        IntStream.range(0, 12).forEach(i -> {
            pob.bowl(MAX_PINS);
            sea.bowl(MAX_PINS);
        });

        assertThat(game.isGameEnd()).isTrue();
    }

    @Test
    @DisplayName("게임 종료x - 한명 게임 종료x")
    void isGameEnd_false() {
        IntStream.range(0, 11).forEach(i -> {
            pob.bowl(MAX_PINS);
            sea.bowl(MAX_PINS);
        });
        pob.bowl(MAX_PINS);

        assertThat(game.isGameEnd()).isFalse();
    }
}