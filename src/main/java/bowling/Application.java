package bowling;

import bowling.domain.Bowling;
import bowling.view.InputView;
import bowling.view.ResultView;

public class Application {

    private static final int CORRECTION_VALUE = 1;

    public static void main(String[] args) {
        String name = InputView.inputPlayerName();
        Bowling bowling = new Bowling();
        ResultView.printBowling(bowling, name);

        while (bowling.isFinal()) {
            int pitching = InputView.inputPitching(bowling.getCurrentFrameNumber() + CORRECTION_VALUE);
            bowling.go(pitching);
            ResultView.printBowling(bowling, name);
        }

        if (bowling.isBonus()) {
            int pitching = InputView.inputPitching(bowling.getCurrentFrameNumber() + CORRECTION_VALUE);
            bowling.go(pitching);
            ResultView.printBowling(bowling, name);
        }
    }
}
