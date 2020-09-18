package bowling.controller;

import bowling.model.BowlingGame;
import bowling.model.User;
import bowling.view.InputView;
import bowling.view.ResultView;

public class BowlingController {

    public static void main(String[] args) {
        BowlingGame bowlingGame = new BowlingGame();
        User user = User.valueOf(InputView.getUserName());

        while (!bowlingGame.isEnd()) {
            int frameNo = bowlingGame.getPlayFrameNo();
            int countOfFallenPins = InputView.getFallenPins(frameNo);
            bowlingGame.bowling(countOfFallenPins);

            ResultView.printScoreBoard(frameNo, user.getName(), bowlingGame.getFrames());
        }
    }

}
