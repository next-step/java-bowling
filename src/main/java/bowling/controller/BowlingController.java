package bowling.controller;

import bowling.domain.Frames;
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

        Frames frames = Frames.of(user);
        resultView.bowlingBoard(frames);

        while(frames.isPlay()) {
            frames.play(inputView.score(frames));
            resultView.bowlingBoard(frames);
        }
    }
}
