package bowling.view;

import bowling.bowl.BowlingGame;
import bowling.pin.Pins;
import bowling.player.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class OutputTest {
    BowlingGame bowlingGame;

    @BeforeEach
    void before(){
        bowlingGame = new BowlingGame(new Player("avx"));
    }

    @Test
    void pitch(){
        // 1
        bowlingGame.pitch(new Pins(10));

        // 2
        bowlingGame.pitch(new Pins(8));
        bowlingGame.pitch(new Pins(2));

        // 3
        bowlingGame.pitch(new Pins(8));
        bowlingGame.pitch(new Pins(1));

        // 4
        bowlingGame.pitch(new Pins(10));

        // 5
        bowlingGame.pitch(new Pins(10));

        // 6
        bowlingGame.pitch(new Pins(10));

        // 7
        bowlingGame.pitch(new Pins(10));

        //8
        bowlingGame.pitch(new Pins(10));

        // 9
        bowlingGame.pitch(new Pins(10));

        // 10
        bowlingGame.pitch(new Pins(1));
        bowlingGame.pitch(new Pins(3));

        Output.printBoard(bowlingGame);
    }
}