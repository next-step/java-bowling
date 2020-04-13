package bowling;

import bowling.view.InputView;

public class BowlingController {
    private static InputView inputView = InputView.getInputView();

    public static void main(String[] args) {
        String userName = inputView.enterUserName();
        System.out.println(userName);

    }
}
