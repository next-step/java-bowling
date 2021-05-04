package bowling.controller;

import bowling.domain.frame.RoundNumber;
import bowling.domain.pin.Pin;
import bowling.domain.player.Player;
import bowling.domain.player.PlayerName;
import bowling.domain.player.Players;
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
        final Players players = players();
        outputView.printScoreBoard(players);

        playGame(players);
    }

    private Players players() {
        final int playerCount = inputView.inputPlayerCount();
        final Players players = Players.create();
        for (int i = 1; i <= playerCount; i++) {
            players.add(Player.from(playerName(i)));
        }
        return players;
    }

    private void playGame(Players players) {
        for (int i = RoundNumber.MIN; i <= RoundNumber.MAX; i++) {
            bowlAll(players, i);
        }
    }

    private void bowlAll(Players players, int roundNumber) {
        for (Player player : players.value()) {
            bowl(players, player, roundNumber);
        }
    }

    private void bowl(Players players, Player player, int roundNumberSource) {
        final RoundNumber roundNumber = new RoundNumber(roundNumberSource);
        while (!player.isEnded(roundNumber)) {
            downPin(player, roundNumber);
            outputView.printScoreBoard(players);
        }
    }

    private PlayerName playerName(int playerCount) {
        try {
            return PlayerName.valueOf(inputView.inputPlayerName(playerCount));
        } catch (PlayerNameValidationException e) {
            System.err.println(e.getMessage());
            return playerName(playerCount);
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
