package bowling.controller;

import bowling.domain.Frames;
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

        Frames frames = Frames.of(user);
        resultView.bowlingBoard(frames);

        while(frames.isPlay()) {
            frames.play(inputView.score(frames));
            resultView.bowlingBoard(frames);
        }
    }
}
