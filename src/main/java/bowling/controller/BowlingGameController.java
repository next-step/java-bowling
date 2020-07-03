package bowling.controller;

import bowling.domain.BowlingGame;
import bowling.domain.player.Player;
import bowling.ui.InputView;
import bowling.ui.ResultView;

public class BowlingGameController {

    public static void main(String[] args) {
        Player player = InputView.inputPlayer();

        BowlingGame bowlingGame = BowlingGame.start();
        ResultView outputView = new ResultView();

        while (!bowlingGame.isFinished()) {
            int frameNumber = bowlingGame.getFrameNumber();
            int downPin = InputView.inputFramePoint(frameNumber);

            bowlingGame.roll(downPin);
            outputView.printResult(player, bowlingGame.getResult());
        }
    }

}
