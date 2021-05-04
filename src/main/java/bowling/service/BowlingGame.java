package bowling.service;

import bowling.domain.frame.RoundNumber;
import bowling.domain.pin.Pin;
import bowling.domain.player.Player;
import bowling.domain.player.Players;
import bowling.exception.BowlingException;
import bowling.view.InputView;
import bowling.view.OutputView;

public final class BowlingGame {

    private final Players players;
    private final InputView inputView;
    private final OutputView outputView;

    public BowlingGame(Players players, InputView inputView, OutputView outputView) {
        this.players = players;
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void printScoreBoard() {
        outputView.printScoreBoard(players);
    }

    public void playGame() {
        for (int i = RoundNumber.MIN; i <= RoundNumber.MAX; i++) {
            bowlAll(i);
        }
    }

    private void bowlAll(int roundNumber) {
        for (Player player : players.value()) {
            bowl(player, roundNumber);
        }
    }

    private void bowl(Player player, int roundNumberSource) {
        final RoundNumber roundNumber = new RoundNumber(roundNumberSource);
        while (!player.isEnded(roundNumber)) {
            downPin(player, roundNumber);
            printScoreBoard();
        }
    }

    private void downPin(final Player player, final RoundNumber roundNumber) {
        try {
            final Pin pin = new Pin(inputView.inputDownPin(player));
            player.knockDownPin(roundNumber, pin);
        } catch (BowlingException e) {
            System.err.println(e.getMessage());
            downPin(player, roundNumber);
        }
    }
}
