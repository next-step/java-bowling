package bowling.controller;

import bowling.domain.User;
import bowling.view.InputView;
import bowling.view.ResultView;

public class BowlingController {
    private InputView inputView;
    private ResultView resultView;

    public BowlingController() {
        inputView = new InputView();
        resultView = new ResultView();
    }

    public void run() {
        String name = inputView.user();
        User user = new User(name);

    }
}
