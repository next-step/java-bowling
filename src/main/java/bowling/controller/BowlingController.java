package bowling.controller;

import bowling.domain.Users;
import bowling.view.ResultView;

public class BowlingController {

    public static void start(Users users) {
        ResultView.printInit(users);
    }
}
