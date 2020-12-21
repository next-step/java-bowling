package bowling.view;

import bowling.domain.Frames;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class GameStatusTest {

    private Frames frames;

    @BeforeEach
    void setUp() {
        frames = new Frames("PJS");
        frames.start();
    }

    @Test
    void blank() {
        Frames frames = new Frames("PJS");
        GameStatus gameStatus = frames.start();
        assertThat(gameStatus.getAllFrameStatus())
                .isEqualTo("|  PJS |      |      |      |      |      |      |      |      |      |      |");
        assertThat(gameStatus.getAllScore())
                .isEqualTo("|      |      |      |      |      |      |      |      |      |      |      |");
    }

    @Test
    void allGutter() {
        for (int i = 1; i <= 18; i++) {
            frames.throwBall(0);
        }
        frames.throwBall(0);
        GameStatus gameStatus = frames.throwBall(0);
        assertThat(gameStatus.getAllFrameStatus())
                .isEqualTo("|  PJS |  -|- |  -|- |  -|- |  -|- |  -|- |  -|- |  -|- |  -|- |  -|- |  -|- |");
        assertThat(gameStatus.getAllScore())
                .isEqualTo("|      |  0   |  0   |  0   |  0   |  0   |  0   |  0   |  0   |  0   |  0   |");
    }

    @Test
    void turkey() {
        frames.throwBall(10);
        frames.throwBall(10);
        GameStatus gameStatus = frames.throwBall(10);
        assertThat(gameStatus.getAllScore())
                .isEqualTo("|      |  30  |      |      |      |      |      |      |      |      |      |");
    }

    @Test
    void strikeAndSpare() {
        frames.throwBall(10);
        frames.throwBall(8);
        frames.throwBall(2);
        frames.throwBall(8);
        GameStatus gameStatus = frames.throwBall(0);
        assertThat(gameStatus.getAllScore())
                .isEqualTo("|      |  20  |  38  |  46  |      |      |      |      |      |      |      |");
    }

    @Nested
    class FirstFrame {

        @Test
        void firstStrike() {
            GameStatus gameStatus = frames.throwBall(10);
            assertThat(gameStatus.getAllFrameStatus())
                    .isEqualTo("|  PJS |  X   |      |      |      |      |      |      |      |      |      |");
            assertThat(gameStatus.getAllScore())
                    .isEqualTo("|      |      |      |      |      |      |      |      |      |      |      |");
        }
        @Test
        void firstThrow() {
            GameStatus gameStatus = frames.throwBall(8);
            assertThat(gameStatus.getAllFrameStatus())
                    .isEqualTo("|  PJS |  8   |      |      |      |      |      |      |      |      |      |");
            assertThat(gameStatus.getAllScore())
                    .isEqualTo("|      |      |      |      |      |      |      |      |      |      |      |");
        }

        @Test
        void firstSpare() {
            frames.throwBall(8);
            GameStatus gameStatus = frames.throwBall(2);
            assertThat(gameStatus.getAllFrameStatus())
                    .isEqualTo("|  PJS |  8|/ |      |      |      |      |      |      |      |      |      |");
            assertThat(gameStatus.getAllScore())
                    .isEqualTo("|      |      |      |      |      |      |      |      |      |      |      |");
        }


        @Test
        void firstMiss() {
            frames.throwBall(8);
            GameStatus gameStatus = frames.throwBall(1);
            assertThat(gameStatus.getAllFrameStatus())
                    .isEqualTo("|  PJS |  8|1 |      |      |      |      |      |      |      |      |      |");
            assertThat(gameStatus.getAllScore())
                    .isEqualTo("|      |  9   |      |      |      |      |      |      |      |      |      |");
        }

        @Test
        void firstGutter() {
            frames.throwBall(8);
            GameStatus gameStatus = frames.throwBall(0);
            assertThat(gameStatus.getAllFrameStatus())
                    .isEqualTo("|  PJS |  8|- |      |      |      |      |      |      |      |      |      |");
            assertThat(gameStatus.getAllScore())
                    .isEqualTo("|      |  8   |      |      |      |      |      |      |      |      |      |");
        }

    }
    @Nested
    class LastFrame {

        @BeforeEach
        void setUp() {
            for (int i = 1; i <= 9; i++) {
                frames.throwBall(10);
            }
        }
        @Test
        void perfect() {
            frames.throwBall(10);
            frames.throwBall(10);
            GameStatus gameStatus = frames.throwBall(10);
            assertThat(gameStatus.getAllFrameStatus())
                    .isEqualTo("|  PJS |  X   |  X   |  X   |  X   |  X   |  X   |  X   |  X   |  X   |  X|X|X |");
            assertThat(gameStatus.getAllScore())
                    .isEqualTo("|      |  30  |  60  |  90  |  120 |  150 |  180 |  210 |  240 |  270 |  300 |");
        }

        @Test
        void lastMiss() {
            frames.throwBall(10);
            frames.throwBall(10);
            GameStatus gameStatus = frames.throwBall(3);
            assertThat(gameStatus.getAllFrameStatus())
                    .isEqualTo("|  PJS |  X   |  X   |  X   |  X   |  X   |  X   |  X   |  X   |  X   |  X|X|3 |");
            assertThat(gameStatus.getAllScore())
                    .isEqualTo("|      |  30  |  60  |  90  |  120 |  150 |  180 |  210 |  240 |  270 |  293 |");
        }

    }

    @Test
    void fullCase() {
        frames.throwBall(1);
        frames.throwBall(4);
        frames.throwBall(4);
        frames.throwBall(5);
        frames.throwBall(6);
        frames.throwBall(4);
        frames.throwBall(5);
        frames.throwBall(5);
        frames.throwBall(10);
        frames.throwBall(0);
        frames.throwBall(1);
        frames.throwBall(7);
        frames.throwBall(3);
        frames.throwBall(6);
        frames.throwBall(4);
        frames.throwBall(10);
        frames.throwBall(2);
        frames.throwBall(8);
        GameStatus gameStatus = frames.throwBall(6);
        assertThat(gameStatus.getAllFrameStatus())
                .isEqualTo("|  PJS |  1|4 |  4|5 |  6|/ |  5|/ |  X   |  -|1 |  7|/ |  6|/ |  X   |  2|/|6 |");
        assertThat(gameStatus.getAllScore())
                .isEqualTo("|      |  5   |  14  |  29  |  49  |  60  |  61  |  77  |  97  |  117 |  133 |");
    }
}
