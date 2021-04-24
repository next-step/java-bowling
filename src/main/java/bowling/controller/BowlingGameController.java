package bowling.controller;

import bowling.domain.Player;
import bowling.view.InputView;
import bowling.view.ResultView;

public class BowlingGameController {

    public void startGame(InputView inputView, ResultView resultView) {
        Player player = Player.initialize(inputView.receivePlayerName());

        while (player.checkPlaying()) {
            int numberOfPines = inputView.receiveNumberOfKnockedDownPins(player.getNextFrameNumber());
            player.throwBall(numberOfPines);
            resultView.printStateOfPlayer(player.export());
        }
    }

}
