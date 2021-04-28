package bowling;

import bowling.domain.Bowling;
import bowling.domain.User;
import bowling.view.InputView;
import bowling.view.ResultView;

public class BowlingGameMain {
    public static void main(String[] args) {
        User user = new User(InputView.getPlayerName());
        Bowling bowling = new Bowling(user);

        while (bowling.isFinished()) {
            int score = InputView.getScore();
            bowling = bowling.addScore(score);
            ResultView.printBowling(bowling);
        }
    }
}
