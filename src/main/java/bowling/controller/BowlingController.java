package bowling.controller;

import bowling.model.BowlingGame;
import bowling.model.Pin;
import bowling.model.Player;
import bowling.view.InputView;
import bowling.view.OutputView;

public class BowlingController {

    private final BowlingGame bowlingGame = new BowlingGame();

    public void run() {
        initBowlingGame();
        playBowlingGame();
    }

    private void initBowlingGame() {
        int playerCount = InputView.inputPlayerCount();
        for (int playerNumber = 1; playerNumber <= playerCount; playerNumber++) {
            bowlingGame.addPlayer(new Player(InputView.inputPlayerName(playerNumber)));
        }
        OutputView.printBowlResults(bowlingGame.getPlayers());
    }

    private void playBowlingGame() {
        while (!bowlingGame.isGameOver()) {
            for (Player player : bowlingGame.getPlayers()) {
                bowl(player);
            }
        }
    }

    private void bowl(Player player) {
        boolean isFinished = false;
        while (!isFinished) {
            Pin pin = Pin.of(InputView.inputPinNumber(player.getName()));
            player.bowl(pin);
            isFinished = player.isCurrentFrameFinished();
            OutputView.printBowlResults(bowlingGame.getPlayers());
        }
    }
}
