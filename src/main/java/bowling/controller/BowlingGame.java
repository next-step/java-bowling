package bowling.controller;

import bowling.view.ConsoleInputView;
import bowling.view.ConsoleOutputView;
import bowling.view.InputView;
import bowling.view.OutputView;

public class BowlingGame {
    public static int FIRST_FRAME = 1;
    public static int LAST_FRAME = 10;

    public void run() {
        InputView inputView = new ConsoleInputView();
        OutputView outputView = new ConsoleOutputView();

        outputView.questionPlayerName();
        String playerName = inputView.playerName();
        outputView.resultsOfBowling();

        Frames frames = new Frames();
        do {
            outputView.questionPinNumber(frame.frameNumber());
            int pinNumber = inputView.pinNumber();
            frames.save(pinNumber);
            outputView.resultsOfBowling(frames);
        } while(frameNumber != LAST_FRAME));
    }
}
