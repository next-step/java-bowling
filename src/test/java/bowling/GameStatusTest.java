package bowling;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class GameStatusTest {
    @Test
    void blank() {
        GameService gameService = new GameService("PJS");
        GameStatus gameStatus = gameService.start();
        assertThat(gameStatus.toString())
                .isEqualTo("|  PJS |      |      |      |      |      |      |      |      |      |      |");
    }
}
