package bowling;

import bowling.domain.Bowling;
import bowling.domain.BowlingLine;
import bowling.domain.RandomStrategy;
import bowling.domain.User;

public class BowlingController {

    public void play() {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        String name = inputView.inputName();
        User user = new User(name);
        Bowling bowling = new Bowling(user);
        BowlingLine bowlingLine = new BowlingLine(bowling, new RandomStrategy());

        outputView.printScore(bowlingLine);
    }
}
