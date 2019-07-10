package bowling;

import bowling.io.InputView;
import bowling.io.OutView;
import bowling.model.BowlingGame;
import bowling.model.Pins;
import bowling.model.Player;

public class Application {

    public static void main(String[] args) {
        String name = InputView.askOfPlayerName();
        Player player = Player.of(name);

        BowlingGame bowlingGame = BowlingGame.settingOf(player);
        OutView.printCurrentScores(bowlingGame.getCurrentStates());

        while (!bowlingGame.isGameOver()){
            int countOfDownPins = InputView.askCountOfDownPins(bowlingGame.getCurrentNumber());
            Pins downPins = Pins.valueOf(countOfDownPins);
            bowlingGame.play(downPins);
            OutView.printCurrentScores(bowlingGame.getCurrentStates());
        }
    }
}
