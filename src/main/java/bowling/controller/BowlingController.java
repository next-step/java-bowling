package bowling.controller;

import bowling.domain.*;
import bowling.view.InputView;
import bowling.view.ResultView;

public class BowlingController {

    private static final int FIRST_ROUND = 1;
    private static final int LAST_ROUND = 10;

    private final InputView inputView;
    private final ResultView resultView;

    public BowlingController(InputView inputView, ResultView resultView) {
        this.inputView = inputView;
        this.resultView = resultView;
    }

    public void run() {
        String name = inputView.name();

        Frames frames = new Frames();
        resultView.print(name, frames.getFrames());

        for (int frameNumber = FIRST_ROUND; frameNumber <= LAST_ROUND; frameNumber++) {
            playRound(frameNumber, name, frames);
        }
    }

    private void playRound(int frameNumber, String name, Frames frames) {
        while (frames.hasRemainTurn(frameNumber)) {
            frames.proceedRound(frameNumber, new PinNumber(inputView.pinNumber(frameNumber)));
            resultView.print(name, frames.getFrames());
        }
    }
}
