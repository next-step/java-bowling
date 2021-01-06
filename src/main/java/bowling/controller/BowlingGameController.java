package bowling.controller;

import bowling.domain.frame.Frames;
import bowling.domain.user.User;
import bowling.view.InputView;
import bowling.view.ResultView;

public class BowlingGameController {

    public void run() {
        ResultView.printAskUserNameMessage();
        User user = new User(InputView.askUserName());

        Frames frames = new Frames();
        ResultView.printScoreBoard(user.makeDTO(), frames.exportResult().makeDTO());

        while (!frames.isEnd()) {
            ResultView.printCurrentFrameNumber(frames.exportResult().makeDTO());
            frames.record(InputView.askScore());
            ResultView.printScoreBoard(user.makeDTO(), frames.exportResult().makeDTO());
        }

    }
}
