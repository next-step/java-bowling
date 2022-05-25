package bowling.controller;

import bowling.domain.Player;
import bowling.view.InputView;

public class BowlingController {
    public void run() {
        Player player = new Player(InputView.inputPlayerName());
    }
}
