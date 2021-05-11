package bowling;

import bowling.controller.BowlingController;
import bowling.domain.Player;
import bowling.view.InputView;

public class BowlingGame {
    public static void main(String[] args) {
        Player player = BowlingController.createPlayer();
        BowlingController.start(player);
    }
}
