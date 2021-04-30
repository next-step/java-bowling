package bowling.controller;

import bowling.domain.frame.RoundNumber;
import bowling.domain.pin.Pin;
import bowling.domain.player.Player;
import bowling.domain.player.PlayerName;
import bowling.exception.BowlingException;
import bowling.exception.PlayerNameValidationException;
import bowling.view.InputView;
import bowling.view.OutputView;

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
            downPin(player, i);
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

    private static void downPin(final Player player, final int roundNumber) {
        try {
            final Pin pin = new Pin(InputView.inputDownPin(roundNumber));
            player.knockDownPin(new RoundNumber(roundNumber), pin);
        } catch (BowlingException e) {
            System.err.println(e.getMessage());
            downPin(player, roundNumber);
        }
    }
}
