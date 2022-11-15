package bowling;

import bowling.view.InputView;
import bowling.view.ResultView;

public class Main {
    public static void main(String[] args) {
        BowlingApp bowlingApp = new BowlingApp(new InputView(), new ResultView());
        bowlingApp.start();
    }
}
