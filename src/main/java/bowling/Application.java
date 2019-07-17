package bowling;

import bowling.io.InputView;
import bowling.io.OutView;
import bowling.model.BowlingGame;
import bowling.model.Pin;
import bowling.model.Player;

public class Application {

    public static void main(String[] args) {
        String name = InputView.askOfPlayerName();
        Player player = Player.of(name);

        BowlingGame bowlingGame = BowlingGame.settingOf(player);
        OutView.printProgress(bowlingGame.results());

        while (!bowlingGame.isGameOver()) {
            int countOfDownPins = InputView.askCountOfDownPins(bowlingGame.getCurrentNumber());
            Pin downPin = Pin.valueOf(countOfDownPins);
            bowlingGame.play(downPin);
            OutView.printProgress(bowlingGame.results());
        }

        OutView.printGameOver();
    }
}
