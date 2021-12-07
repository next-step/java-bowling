package bowling.controller;

import bowling.domain.Game;
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
        Game game = new Game(playerName);
        outputView.resultsOfBowling(game);

        do {
            inputView.questionPinNumber(game.frameNumber());
            int pinNumber = inputView.pinNumber();
            game.pitch(pinNumber);
            outputView.resultsOfBowling(game);
        } while(!game.finished());
    }
}
