package bowling;

import bowling.domain.Bowling;
import bowling.view.InputView;
import bowling.view.ResultView;

public class Application {

    public static void main(String[] args) {
        String name = InputView.inputPlayerName();
        Bowling bowling = new Bowling();
        ResultView.printBowling(bowling, name);

        while (true) {
            int pitching = InputView.inputPitching(bowling.getCurrentFrame() + 1);
            bowling.go(pitching);
            ResultView.printBowling(bowling, name);
        }
    }
}
