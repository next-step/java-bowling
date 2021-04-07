package bowling.controller;

import bowling.domain.User;
import bowling.view.InputView;
import bowling.view.ResultView;

public class BowlingController {
    private InputView inputView;
    private ResultView resultView;

    public BowlingController() {
        this.inputView = new InputView();
        this.resultView = new ResultView();
    }

    public void run() {
        User user = inputView.user();

        resultView.bowlingBoard(user);

        while (user.isPlay()) {
            user = user.play(inputView.score(user.frames()));
            resultView.bowlingBoard(user);
        }
    }
}
