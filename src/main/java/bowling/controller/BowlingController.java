package bowling.controller;

import bowling.domain.player.Player;
import bowling.view.InputView;
import bowling.view.ResultView;

public class BowlingController {
    public void start() {
        Player player = getPlayer();
        ResultView.printFrameResult(player);

        while (!player.isGameEnd()) {
            inputBowlNumberAndPrintResult(player);
        }
    }

    private Player getPlayer() {
        String playerName = InputView.inputPlayerName();

        try {
            return new Player(playerName);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            return getPlayer();
        }
    }

    private void inputBowlNumberAndPrintResult(Player player) {
        while (player.canBowl()) {
            bowl(player);
            ResultView.printFrameResult(player);
        }
    }

    private void bowl(Player player) {
        int number = InputView.inputBowlNumber(player.getName().getValue());

        try {
            player.bowl(number);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }
}
