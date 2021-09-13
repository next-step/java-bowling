package bowling.controller;

import bowling.domain.UserName;
import bowling.view.InputView;
import bowling.view.ResultView;

public class BowlingController {

    public static void startBowling() {
        UserName userName = InputView.getUserName();
        System.out.println(userName);

        ResultView.printHeader();
    }
}
