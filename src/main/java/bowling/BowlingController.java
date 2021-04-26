package bowling;

import bowling.domain.player.Player;
import bowling.domain.player.PlayerName;
import bowling.exception.PlayerNameValidationException;

public final class BowlingController {

    private BowlingController() {}

    public static void run() {
        final Player player = new Player(playerName(InputView.inputPlayerName()));
        OutputView.printScoreBoard(player);
    }

    private static PlayerName playerName(final String playerName) {
        try {
            return PlayerName.valueOf(playerName);
        } catch (PlayerNameValidationException e) {
            System.err.println(e.getMessage());
            return playerName(InputView.inputPlayerName());
        }
    }
}
