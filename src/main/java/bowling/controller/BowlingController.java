package bowling.controller;

import bowling.view.InputView;
import bowling.view.ResultView;

public class BowlingController {
    public void start() {
        String userName = InputView.userNameInput();
        ResultView.bowlingFrameListPrint();
        ResultView.userBowlingFrameListPrint(userName);
    }
}
