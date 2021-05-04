package bowling.domain;

import bowling.domain.controller.BowlingController;
import bowling.domain.view.InputView;

public class BowlingGame {
    public static void main(String[] args) {
        Player player = new Player(InputView.getPlayer());
        BowlingController.start(player);
    }
}
