package bowling.controller;

import bowling.domain.player.Player;
import bowling.ui.InputView;
import bowling.ui.ResultView;

public class BowlingGameController {

    public static void main(String[] args) {
        Player player = InputView.inputPlayer();
        ResultView.printDefaultScoreBoard(player);
    }
}
