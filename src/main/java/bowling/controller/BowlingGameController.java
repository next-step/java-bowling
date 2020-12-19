package bowling.controller;

import bowling.domain.Player;
import bowling.view.InputView;

public class BowlingGameController {

    public static void run() {
        String playerName = getPlayerName();
        Player player = Player.from(playerName);

    }

    private static String getPlayerName() {
        return InputView.inputPlayerName();
    }
}
