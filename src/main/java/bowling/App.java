package bowling;

import bowling.domain.Hit;
import bowling.domain.Name;
import bowling.domain.Round;
import bowling.view.InputView;
import bowling.view.ResultView;

public class App {

    public static void main(String[] args) {
        Name name = InputView.inputName();

        Round round = new Round();
        ResultView.printRound(name, round);
        while (!round.isEnd()) {
            Hit hit = InputView.inputPins(round.getCurrentFrameNumber());
            round.hit(hit);
            ResultView.printRound(name, round);
        }
    }
}
