package bowling.controller;

import bowling.domain.User;
import bowling.domain.Users;
import bowling.view.InputView;
import bowling.view.ResultView;

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

        for (int i = 0; i < LAST_FRAME_NUMBER; i++) {
            int frameNumber = i;
            users.forEach(user -> bowlingPlay(frameNumber, users, user));
        }
    }

    public void bowlingPlay(int frameNumber, Users users, User user) {
        while (!user.isFrameCompleted(frameNumber)) {
            user.play(inputView.score(user));
            resultView.bowlingBoard(users);
        }
    }
}
