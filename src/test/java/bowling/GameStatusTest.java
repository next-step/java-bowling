package bowling;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class GameStatusTest {
    @Test
    void blank() {
        GameService gameService = new GameService("PJS");
        GameStatus gameStatus = gameService.start();
        assertThat(gameStatus.toString())
                .isEqualTo("|  PJS |      |      |      |      |      |      |      |      |      |      |");
    }

    @Test
    void firstStrike() {
        GameService gameService = new GameService("PJS");
        gameService.start();
        GameStatus gameStatus = gameService.throwBall(10);
        assertThat(gameStatus.toString())
                .isEqualTo("|  PJS |  X   |      |      |      |      |      |      |      |      |      |");
    }
}
