package bowling;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class GameStatusTest {

    private GameService gameService;

    @BeforeEach
    void setUp() {
        gameService = new GameService("PJS");
        gameService.start();
    }

    @Test
    void blank() {
        GameService gameService = new GameService("PJS");
        GameStatus gameStatus = gameService.start();
        assertThat(gameStatus.toString())
                .isEqualTo("|  PJS |      |      |      |      |      |      |      |      |      |      |");
    }

    @Test
    void allGutter() {
        for (int i = 1; i <= 18; i++) {
            gameService.throwBall(0);
        }
        gameService.throwBall(0);
        GameStatus gameStatus = gameService.throwBall(0);
        assertThat(gameStatus.toString())
                .isEqualTo("|  PJS |  -|- |  -|- |  -|- |  -|- |  -|- |  -|- |  -|- |  -|- |  -|- |  -|- |");
    }

    @Nested
    class FirstFrame {
        @Test
        void firstStrike() {
            GameStatus gameStatus = gameService.throwBall(10);
            assertThat(gameStatus.toString())
                    .isEqualTo("|  PJS |  X   |      |      |      |      |      |      |      |      |      |");
        }

        @Test
        void firstThrow() {
            GameStatus gameStatus = gameService.throwBall(8);
            assertThat(gameStatus.toString())
                    .isEqualTo("|  PJS |  8   |      |      |      |      |      |      |      |      |      |");
        }

        @Test
        void firstSpare() {
            gameService.throwBall(8);
            GameStatus gameStatus = gameService.throwBall(2);
            assertThat(gameStatus.toString())
                    .isEqualTo("|  PJS |  8|/ |      |      |      |      |      |      |      |      |      |");
        }


        @Test
        void firstMiss() {
            gameService.throwBall(8);
            GameStatus gameStatus = gameService.throwBall(1);
            assertThat(gameStatus.toString())
                    .isEqualTo("|  PJS |  8|1 |      |      |      |      |      |      |      |      |      |");
        }

        @Test
        void firstGutter() {
            gameService.throwBall(8);
            GameStatus gameStatus = gameService.throwBall(0);
            assertThat(gameStatus.toString())
                    .isEqualTo("|  PJS |  8|- |      |      |      |      |      |      |      |      |      |");
        }
    }
    @Nested
    class LastFrame {
        @BeforeEach
        void setUp() {
            for (int i = 1; i <= 9; i++) {
                gameService.throwBall(10);
            }
        }

        @Test
        void perfect() {
            gameService.throwBall(10);
            gameService.throwBall(10);
            GameStatus gameStatus = gameService.throwBall(10);
            assertThat(gameStatus.toString())
                    .isEqualTo("|  PJS |  X   |  X   |  X   |  X   |  X   |  X   |  X   |  X   |  X   |  X|X|X |");
        }

        @Test
        void lastMiss() {
            gameService.throwBall(10);
            gameService.throwBall(10);
            GameStatus gameStatus = gameService.throwBall(3);
            assertThat(gameStatus.toString())
                    .isEqualTo("|  PJS |  X   |  X   |  X   |  X   |  X   |  X   |  X   |  X   |  X   |  X|X|3 |");
        }
    }
}
