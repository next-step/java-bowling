package bowling.controller;

import bowling.domain.pin.Pin;
import bowling.domain.player.Player;
import bowling.domain.player.PlayerName;
import bowling.exception.BowlingException;
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

        final BowlingTemplate bowlingTemplate =
                new BowlingTemplate(this::pin, () -> outputView.printScoreBoard(bowlingGame.scoreBoard()));
        bowlingGame.playGame(bowlingTemplate);
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
