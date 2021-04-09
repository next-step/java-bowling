package bowling.controller;

import bowling.domain.User;
import bowling.domain.Users;
import bowling.view.InputView;
import bowling.view.ResultView;

import java.util.stream.IntStream;

public class BowlingController {
    private static int LAST_FRAME_NUMBER = 10;
    private InputView inputView;
    private ResultView resultView;

    public BowlingController() {
        this.inputView = new InputView();
        this.resultView = new ResultView();
    }

    public void run() {
        Users users = inputView.users();

        resultView.bowlingBoard(users);

        IntStream.range(0, LAST_FRAME_NUMBER)
                .forEach(frameIndex -> users.forEach(user -> bowlingPlay(frameIndex, users, user)));
    }

    public void bowlingPlay(int frameIndex, Users users, User user) {
        while (!user.isFrameCompleted(frameIndex)) {
            user.play(inputView.score(user));
            resultView.bowlingBoard(users);
        }
    }
}
