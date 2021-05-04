package bowling.controller;

import bowling.domain.player.Player;
import bowling.domain.player.PlayerName;
import bowling.domain.player.Players;
import bowling.exception.PlayerNameValidationException;
import bowling.service.BowlingGame;
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
        final BowlingGame bowlingGame = new BowlingGame(players(), inputView, outputView);
        bowlingGame.printScoreBoard();
        bowlingGame.playGame();
    }

    private Players players() {
        final int playerCount = inputView.inputPlayerCount();
        final Players players = Players.create();
        for (int i = 1; i <= playerCount; i++) {
            players.add(Player.from(playerName(i)));
        }
        return players;
    }

    private PlayerName playerName(int playerCount) {
        try {
            return PlayerName.valueOf(inputView.inputPlayerName(playerCount));
        } catch (PlayerNameValidationException e) {
            System.err.println(e.getMessage());
            return playerName(playerCount);
        }
    }
}
