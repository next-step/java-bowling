package bowling;

import bowling.domain.Bowling;
import bowling.ui.InputView;
import bowling.ui.ResultView;

import java.util.Collections;

public class BowlingGameApplication {
    public static void main(String[] args) {
        Bowling bowling = new Bowling();
        String name = InputView.inputPlayerName();
        ResultView.printBoard(name, Collections.emptyList());

        while (!bowling.isGameEnd()) {
            int fallenPins = InputView.inputFallenPins(bowling.getCurrentFrameNumber());
            ResultView.printBoard(name, bowling.roll(fallenPins));
        }
    }
}
