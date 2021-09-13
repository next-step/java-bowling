package bowling.controller;

import bowling.domain.UserName;
import bowling.view.InputView;

public class BowlingController {

    public static void startBowling() {
        UserName userName = InputView.getUserName();
        System.out.println(userName);
    }
}
