package bowling.view;

import bowling.GameService;
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
        assertThat(gameStatus.getScoring())
                .isEqualTo("|  PJS |      |      |      |      |      |      |      |      |      |      |");
        assertThat(gameStatus.getScore())
                .isEqualTo("|      |      |      |      |      |      |      |      |      |      |      |");
    }

    @Test
    void allGutter() {
        for (int i = 1; i <= 18; i++) {
            gameService.throwBall(0);
        }
        gameService.throwBall(0);
        GameStatus gameStatus = gameService.throwBall(0);
        assertThat(gameStatus.getScoring())
                .isEqualTo("|  PJS |  -|- |  -|- |  -|- |  -|- |  -|- |  -|- |  -|- |  -|- |  -|- |  -|- |");
        assertThat(gameStatus.getScore())
                .isEqualTo("|      |  0   |  0   |  0   |  0   |  0   |  0   |  0   |  0   |  0   |  0   |");
    }

    @Test
    void turkey() {
        gameService.throwBall(10);
        gameService.throwBall(10);
        GameStatus gameStatus = gameService.throwBall(10);
        assertThat(gameStatus.getScore())
                .isEqualTo("|      |  30  |      |      |      |      |      |      |      |      |      |");
    }

    @Test
    void strikeAndSpare() {
        gameService.throwBall(10);
        gameService.throwBall(8);
        gameService.throwBall(2);
        gameService.throwBall(8);
        GameStatus gameStatus = gameService.throwBall(0);
        assertThat(gameStatus.getScore())
                .isEqualTo("|      |  20  |  38  |  46  |      |      |      |      |      |      |      |");
    }

    @Nested
    class FirstFrame {

        @Test
        void firstStrike() {
            GameStatus gameStatus = gameService.throwBall(10);
            assertThat(gameStatus.getScoring())
                    .isEqualTo("|  PJS |  X   |      |      |      |      |      |      |      |      |      |");
            assertThat(gameStatus.getScore())
                    .isEqualTo("|      |      |      |      |      |      |      |      |      |      |      |");
        }
        @Test
        void firstThrow() {
            GameStatus gameStatus = gameService.throwBall(8);
            assertThat(gameStatus.getScoring())
                    .isEqualTo("|  PJS |  8   |      |      |      |      |      |      |      |      |      |");
            assertThat(gameStatus.getScore())
                    .isEqualTo("|      |      |      |      |      |      |      |      |      |      |      |");
        }

        @Test
        void firstSpare() {
            gameService.throwBall(8);
            GameStatus gameStatus = gameService.throwBall(2);
            assertThat(gameStatus.getScoring())
                    .isEqualTo("|  PJS |  8|/ |      |      |      |      |      |      |      |      |      |");
            assertThat(gameStatus.getScore())
                    .isEqualTo("|      |      |      |      |      |      |      |      |      |      |      |");
        }


        @Test
        void firstMiss() {
            gameService.throwBall(8);
            GameStatus gameStatus = gameService.throwBall(1);
            assertThat(gameStatus.getScoring())
                    .isEqualTo("|  PJS |  8|1 |      |      |      |      |      |      |      |      |      |");
            assertThat(gameStatus.getScore())
                    .isEqualTo("|      |  9   |      |      |      |      |      |      |      |      |      |");
        }

        @Test
        void firstGutter() {
            gameService.throwBall(8);
            GameStatus gameStatus = gameService.throwBall(0);
            assertThat(gameStatus.getScoring())
                    .isEqualTo("|  PJS |  8|- |      |      |      |      |      |      |      |      |      |");
            assertThat(gameStatus.getScore())
                    .isEqualTo("|      |  8   |      |      |      |      |      |      |      |      |      |");
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
            assertThat(gameStatus.getScoring())
                    .isEqualTo("|  PJS |  X   |  X   |  X   |  X   |  X   |  X   |  X   |  X   |  X   |  X|X|X |");
            assertThat(gameStatus.getScore())
                    .isEqualTo("|      |  30  |  60  |  90  |  120 |  150 |  180 |  210 |  240 |  270 |  300 |");
        }

        @Test
        void lastMiss() {
            gameService.throwBall(10);
            gameService.throwBall(10);
            GameStatus gameStatus = gameService.throwBall(3);
            assertThat(gameStatus.getScoring())
                    .isEqualTo("|  PJS |  X   |  X   |  X   |  X   |  X   |  X   |  X   |  X   |  X   |  X|X|3 |");
            assertThat(gameStatus.getScore())
                    .isEqualTo("|      |  30  |  60  |  90  |  120 |  150 |  180 |  210 |  240 |  270 |  293 |");
        }

    }

    @Test
    void fullCase() {
        gameService.throwBall(1);
        gameService.throwBall(4);
        gameService.throwBall(4);
        gameService.throwBall(5);
        gameService.throwBall(6);
        gameService.throwBall(4);
        gameService.throwBall(5);
        gameService.throwBall(5);
        gameService.throwBall(10);
        gameService.throwBall(0);
        gameService.throwBall(1);
        gameService.throwBall(7);
        gameService.throwBall(3);
        gameService.throwBall(6);
        gameService.throwBall(4);
        gameService.throwBall(10);
        gameService.throwBall(2);
        gameService.throwBall(8);
        GameStatus gameStatus = gameService.throwBall(6);
        assertThat(gameStatus.getScoring())
                .isEqualTo("|  PJS |  1|4 |  4|5 |  6|/ |  5|/ |  X   |  -|1 |  7|/ |  6|/ |  X   |  2|/|6 |");
        assertThat(gameStatus.getScore())
                .isEqualTo("|      |  5   |  14  |  29  |  49  |  60  |  61  |  77  |  97  |  117 |  133 |");
    }
}
