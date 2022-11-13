package bowling;

import bowling.domain.Bowling;
import bowling.domain.BowlingLine;
import bowling.domain.strategy.RandomStrategy;
import bowling.domain.User;
import bowling.view.InputView;
import bowling.view.OutputView;

public class BowlingController {

    public void play() {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        String name = inputView.inputName();
        User user = new User(name);
        Bowling bowling = new Bowling(user);
        BowlingLine bowlingLine = new BowlingLine(bowling, new RandomStrategy());

        outputView.printRecord(bowlingLine);
    }
}
