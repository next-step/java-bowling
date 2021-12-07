package bowling.controller;

import bowling.domain.Frames;
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

        inputView.questionPlayerName();
        String playerName = inputView.playerName();
        Frames frames = new Frames();
        outputView.resultsOfBowling(frames);

        do {
            inputView.questionPinNumber(frames.frameNumber());
            int pinNumber = inputView.pinNumber();
            frames.pitch(pinNumber);
            outputView.resultsOfBowling(frames);
        } while(!frames.finished());
    }
}
