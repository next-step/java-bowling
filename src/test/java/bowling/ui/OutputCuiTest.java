package bowling.ui;

import bowling.domain.Frames;
import bowling.domain.Game;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OutputCuiTest {

    private Game game;

    @BeforeEach
    void setUp() {
        game = new Game("tes");
        game.throwBall(10); // 1
        game.throwBall(10);
        game.throwBall(10);
        game.throwBall(10);
        game.throwBall(10); // 5
        game.throwBall(10);
        game.throwBall(10);
        game.throwBall(10);
        game.throwBall(10); // 9
    }

    @DisplayName("X|XX")
    @Test
    void finalFrameDrawTest1() {
        OutputCui.drawStatus(game);

        game.throwBall(10);
        game.throwBall(10);
        game.throwBall(10);
        OutputCui.drawStatus(game);
        System.out.println();
    }

    @DisplayName("5|/5")
    @Test
    void finalFrameDrawTest2() {
        OutputCui.drawStatus(game);

        game.throwBall(5);
        game.throwBall(5);
        game.throwBall(5);
        OutputCui.drawStatus(game);
        System.out.println();
    }

    @DisplayName("5|/X")
    @Test
    void finalFrameDrawTest3() {
        OutputCui.drawStatus(game);

        game.throwBall(5);
        game.throwBall(5);
        game.throwBall(10);
        OutputCui.drawStatus(game);
        System.out.println();
    }

}
