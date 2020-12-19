package bowling;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class GameServiceTest {
    @Test
    void illegalPlayerName() {
        Assertions.assertThatThrownBy(() -> new GameService("ABCD"))
                .isInstanceOf(IllegalPlayerNameException.class);
    }

    @Test
    void isFinish() {
        GameService gameService = new GameService("ABC");
        gameService.start();
        for (int i = 1; i <= 12; i++) {
            assertThat(gameService.isFinish()).isFalse();
            gameService.throwBall(10);
        }

        assertThat(gameService.isFinish()).isTrue();
    }
}
