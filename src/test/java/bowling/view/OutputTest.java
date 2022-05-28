package bowling.view;

import bowling.domain.BowlingGame;
import bowling.domain.pin.Pins;
import bowling.domain.player.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/*
| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |
|  avx |  X   |  8|/ |  8|1 |  X   |  X   |  X   |  -|- |  X   |  X   |  X|2|/ |
|      |  20  |  38  |  47  |  77  |  97  |  107 |  107 |  137 |  159 |  179 |
*/

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
        bowlingGame.pitch(new Pins(0));
        bowlingGame.pitch(new Pins(0));

        //8
        bowlingGame.pitch(new Pins(10));

        // 9
        bowlingGame.pitch(new Pins(10));

        // 10
        bowlingGame.pitch(new Pins(10));
        bowlingGame.pitch(new Pins(2));
        bowlingGame.pitch(new Pins(8));

        Output.printBoard(bowlingGame);
    }
}