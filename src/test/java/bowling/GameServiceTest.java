package bowling;

import bowling.domain.IllegalBallThrownException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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

    @DisplayName("게임이 끝났는데 공을 던지면 예외가 발생한다")
    @Test
    void isOverThrow() {
        GameService gameService = new GameService("ABC");
        gameService.start();
        for (int i = 1; i <= 12; i++) {
            gameService.throwBall(10);
        }

        assertThatThrownBy(() -> gameService.throwBall(10))
                .isInstanceOf(IllegalBallThrownException.class);
    }
}
