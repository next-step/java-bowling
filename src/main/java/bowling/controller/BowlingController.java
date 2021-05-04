package bowling.controller;

import bowling.domain.frame.RoundNumber;
import bowling.domain.pin.Pin;
import bowling.domain.player.Player;
import bowling.domain.player.PlayerName;
import bowling.exception.BowlingException;
import bowling.view.BowlingGame;
import bowling.view.InputView;
import bowling.view.OutputView;

import java.util.List;

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
        final int playerCount = inputView.inputPlayerCount();
        final List<PlayerName> playerNames = inputView.inputPlayerNames(playerCount);

        final BowlingGame bowlingGame = new BowlingGame(playerNames);
        outputView.printScoreBoard(bowlingGame.scoreBoard());

        for (int i = RoundNumber.MIN; i <= RoundNumber.MAX; i++) {
            bowlAll(bowlingGame, i);
        }
    }

    private void bowlAll(BowlingGame bowlingGame, int roundNumber) {
        for (Player player : bowlingGame.players()) {
            bowl(bowlingGame, roundNumber, player);
        }
    }

    private void bowl(BowlingGame bowlingGame, int roundNumberSource, Player player) {
        final RoundNumber roundNumber = new RoundNumber(roundNumberSource);
        final BowlingTemplate bowlingTemplate =
                new BowlingTemplate(this::pin, () -> outputView.printScoreBoard(bowlingGame.scoreBoard()));
        while (!player.isEnded(roundNumber)) {
            downPin(player, roundNumber, bowlingTemplate);
        }
    }

    private void downPin(final Player player, final RoundNumber roundNumber, final BowlingTemplate bowlingTemplate) {
        try {
            bowlingTemplate.execute(player, pin -> player.knockDownPin(roundNumber, pin));
        } catch (BowlingException e) {
            System.err.println(e.getMessage());
            downPin(player, roundNumber, bowlingTemplate);
        }
    }

    private Pin pin(Player player) {
        try {
            return new Pin(inputView.inputDownPin(player));
        } catch (BowlingException e) {
            System.err.println(e.getMessage());
            return pin(player);
        }
    }
}
