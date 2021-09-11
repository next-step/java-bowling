package bowling.model;

import bowling.view.InputView;
import bowling.view.ResultView;

public class BowlingGameApp {
    public static void main(String[] args) {
        String playerName = InputView.inputPlayerName();
        ResultView.printBoard(playerName);
        BowlingGame bowlingGame = new BowlingGame(playerName);

        while (bowlingGame.canPlayNext()) {
            int fallenPinCount = InputView.inputFallenPinCount(bowlingGame.nextFrameNumber());
            bowlingGame.play(fallenPinCount);
            ResultView.printFrames(playerName, bowlingGame.frames());
        }
    }
}
