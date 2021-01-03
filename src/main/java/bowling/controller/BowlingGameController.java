package bowling.controller;

import bowling.domain.bowlinggame.BowlingGameBoard;
import bowling.domain.user.User;
import bowling.view.InputView;
import bowling.view.ResultView;

public class BowlingGameController {

    public void run() {
        ResultView.printAskUserNameMessage();
        User user = new User(InputView.askUserName());

        BowlingGameBoard bowlingGameBoard = new BowlingGameBoard();
        ResultView.printScoreBoard(user.makeDTO(), bowlingGameBoard.exportStatus().makeDTO());

        while (!bowlingGameBoard.isEnd()) {
            ResultView.printCurrentFrameNumber(bowlingGameBoard.exportStatus().makeDTO());
            bowlingGameBoard.record(InputView.askScore());
            ResultView.printScoreBoard(user.makeDTO(), bowlingGameBoard.exportStatus().makeDTO());
        }

    }
}
