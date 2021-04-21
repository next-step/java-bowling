package bowling.controller;

import bowling.domain.Player;
import bowling.view.InputView;

public class BowlingController {

    private InputView inputView;

    public BowlingController() {
        this.inputView = new InputView();
    }

    public void run() {
        Player player = Player.from(inputView.inputPlayer());

    }
}
