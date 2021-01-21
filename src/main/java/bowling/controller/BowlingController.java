package bowling.controller;

import bowling.domain.frame.ScoreBoard;
import bowling.domain.user.User;
import bowling.view.InputView;
import bowling.view.ResultView;

public class BowlingController {

    public void run() {
        User user = new User(InputView.askName());
        ScoreBoard board = new ScoreBoard();
        ResultView.printResults(user.getName(), board.getResults());

        while (!board.isEnd()) {
            board.record(InputView.askDownedPins());
            ResultView.printResults(user.getName(), board.getResults());
        }
    }
}
