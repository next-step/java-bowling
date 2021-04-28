package bowling;

import bowling.domain.Bowling;
import bowling.domain.User;
import bowling.view.InputView;
import bowling.view.ResultView;

public class BowlingGameMain {
    public static void main(String[] args) throws Exception {
        User user = new User(InputView.getPlayerName());
        Bowling bowling = new Bowling(user);
        ResultView.printBowling(bowling);

        while (!bowling.isFinished()) {
            int score = InputView.getScore(bowling.nowFrame() + 1);
            bowling = bowling.addScore(score);
            ResultView.printBowling(bowling);
        }
    }
}
