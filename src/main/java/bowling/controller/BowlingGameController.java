package bowling.controller;

import bowling.model.Player;
import bowling.view.InputView;

public class BowlingGameController {

    private final InputView inputView;

    public BowlingGameController() {
        this(new InputView());
    }


    public BowlingGameController(InputView inputView) {
        this.inputView = inputView;
    }

    public void start() {
        Player player = inputView.inputPlayerName();
        System.out.println(player);
    }

}
