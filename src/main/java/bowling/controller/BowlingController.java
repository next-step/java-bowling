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

    private final InputView inputView;
    private final OutputView outputView;

    public BowlingController() {
        this(new InputView(), new OutputView());
    }

    public BowlingController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        final Player player = Player.from(playerName());
        outputView.printScoreBoard(player);

        for (int i = RoundNumber.MIN; i <= RoundNumber.MAX; i++) {
            bowl(player, i);
        }
    }

    private void bowl(Player player, int i) {
        final RoundNumber roundNumber = new RoundNumber(i);
        while (!player.isEnded(roundNumber)) {
            downPin(player, i);
            outputView.printScoreBoard(player);
        }
    }

    private PlayerName playerName() {
        try {
            return PlayerName.valueOf(inputView.inputPlayerName());
        } catch (PlayerNameValidationException e) {
            System.err.println(e.getMessage());
            return playerName();
        }
    }

    private void downPin(final Player player, final int roundNumber) {
        try {
            final Pin pin = new Pin(inputView.inputDownPin(roundNumber));
            player.knockDownPin(new RoundNumber(roundNumber), pin);
        } catch (BowlingException e) {
            System.err.println(e.getMessage());
            downPin(player, roundNumber);
        }
    }
}
