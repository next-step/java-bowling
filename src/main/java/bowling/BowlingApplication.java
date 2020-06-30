package bowling;

import bowling.domain.Player;
import bowling.domain.BowlingGame;
import bowling.ui.InputView;
import bowling.ui.ResultView;

public class BowlingApplication {
    public static void main(String[] args) {
        String name = InputView.enterName();
        Player player = Player.of(name);
        BowlingGame bowlingGame = BowlingGame.start(player);

        while (!bowlingGame.isLastFrame()) {
            int downPin = InputView.enterDownPin(bowlingGame.getCurrentIndex());
            bowlingGame.run(downPin);
            ResultView.printResult(bowlingGame);
        }

    }
}
