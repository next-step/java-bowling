package bowling.controller;

import bowling.domain.Player;
import bowling.view.InputView;

public class BowlingController {

    public void start() {
        InputView inputView = new InputView();

        Player player = inputView.inputPlayer();
    }
}
