package bowling;

import bowling.service.BowlingGame;
import bowling.view.InputView;
import bowling.view.ResultView;

public class BowlingApplication {

    private final InputView inputView;
    private final ResultView resultView;

    public BowlingApplication() {
        this.inputView = new InputView();
        this.resultView = new ResultView();
    }

    public void run() {
        String name = inputView.inputUserName();
        resultView.printBoard(name);
        BowlingGame bowling = new BowlingGame();

        while (!bowling.isLast()) {
            int downPins = inputView.inputPitch(bowling.frameNo());
            bowling.bowl(downPins);
            resultView.printCustomResult(bowling, name);
        }
    }

    public static void main(String[] args) {
        new BowlingApplication().run();
    }

}
