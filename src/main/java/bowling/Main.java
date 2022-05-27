package bowling;

import bowling.domain.BowlingGame;
import bowling.domain.pin.Pins;
import bowling.view.Input;
import bowling.view.Output;

public class Main {
    public static void main(String[] args) {

        BowlingGame bowlingGame = new BowlingGame(Input.readPlayerName());

        while (bowlingGame.hasNext()){
            Pins pins = Input.readHitPin(bowlingGame);
            bowlingGame.pitch(pins);
            Output.printBoard(bowlingGame);
        }
    }
}
