package bowling;

import bowling.bowl.BowlingGame;
import bowling.pin.Pins;
import bowling.view.Input;
import bowling.view.Output;

public class BowlingController {
    public void start(){
        BowlingGame bowlingGame = new BowlingGame(Input.readPlayerName());

        while (bowlingGame.hasNext()){
            Pins pins = Input.readHitPin(bowlingGame);
            bowlingGame.pitch(pins);
            Output.printBoard(bowlingGame);
        }
    }
}
