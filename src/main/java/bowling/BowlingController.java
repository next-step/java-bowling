package bowling;

import bowling.view.InputView;
import bowling.view.ResultView;

public class BowlingController {
    private static InputView inputView = InputView.getInputView();
    private static ResultView resultView = ResultView.getResultView();

    public static void main(String[] args) {
        String userName = inputView.enterUserName();
        System.out.println(resultView.initScoreBoard(userName));

    }
}
