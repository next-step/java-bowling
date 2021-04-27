package bowling;

import bowling.domain.frame.RoundNumber;
import bowling.domain.pin.Pin;
import bowling.domain.player.Player;
import bowling.domain.player.PlayerName;
import bowling.exception.BowlingException;
import bowling.exception.PlayerNameValidationException;

public final class BowlingController {

    private BowlingController() {}

    public static void run() {
        final Player player = new Player(playerName());
        OutputView.printScoreBoard(player);

        for (int i = RoundNumber.MIN; i <= RoundNumber.MAX; i++) {
            bowl(player, i);
        }
    }

    private static void bowl(Player player, int i) {
        final RoundNumber roundNumber = new RoundNumber(i);
        while (!player.isEnded(roundNumber)) {
            final Pin pin = downPin(i);
            OutputView.printScoreBoard(player);
        }
    }

    private static PlayerName playerName() {
        try {
            return PlayerName.valueOf(InputView.inputPlayerName());
        } catch (PlayerNameValidationException e) {
            System.err.println(e.getMessage());
            return playerName();
        }
    }

    private static Pin downPin(final int roundNumber) {
        try {
            return new Pin(InputView.inputDownPin(roundNumber));
        } catch (BowlingException e) {
            System.err.println(e.getMessage());
            return downPin(roundNumber);
        }
    }
}
