package bowling.controller;

import bowling.domain.User;
import bowling.domain.Users;
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
        Users users = inputView.users();

        resultView.bowlingBoard(users);

        while (users.isAllPlay()) {
            users.users().forEach(user -> bowlingPlay(users, user));
        }
    }

    public void bowlingPlay(Users users, User user) {

        while (true) {
            user.play(inputView.score(user));
            resultView.bowlingBoard(users);

            if (user.isEndFrame()) {
                break;
            }
        }
    }
}
