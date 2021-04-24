package bowling;

import bowling.controller.BowlingGameController;
import bowling.view.ConsoleInputView;
import bowling.view.ConsoleResultView;
import bowling.view.InputView;
import bowling.view.ResultView;

public class BowlingGameApplication {

    public static void main(String[] args) {
        InputView inputView = new ConsoleInputView();
        ResultView resultView = new ConsoleResultView();

        BowlingGameController controller = new BowlingGameController();
        controller.startGame(inputView, resultView);
    }
}
