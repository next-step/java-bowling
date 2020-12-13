package bowling;

import bowling.view.ConsoleInputView;
import bowling.view.ConsoleResultView;
import bowling.view.InputView;
import bowling.view.ResultView;

public class ApplicationRunner {
    public static void main(String[] args) {
        GameHandler gameHandler = getGameHandler();
        gameHandler.run();
    }

    private static GameHandler getGameHandler() {
        return new GameHandler(getInputView(), getResultView());
    }

    private static InputView getInputView() {
        return new ConsoleInputView();
    }

    private static ResultView getResultView() {
        return new ConsoleResultView();
    }
}
