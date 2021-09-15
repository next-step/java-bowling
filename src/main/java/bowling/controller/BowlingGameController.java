package bowling.controller;

import bowling.domain.BowlingGame;
import bowling.domain.BowlingGames;
import bowling.view.InputView;
import bowling.view.ResultView;

public class BowlingGameController {
    public void startGame() {
        int playCount = InputView.inputPlayerCount();
        BowlingGames bowlingGames = new BowlingGames(InputView.inputPlayerName(playCount));

        ResultView.printInit(bowlingGames);
        while (!bowlingGames.isEnd()) {
            playBowling(bowlingGames);
        }
    }

    private void playBowling(BowlingGames bowlingGames) {
        for (BowlingGame bowlingGame : bowlingGames.games()) {
            playByPlayer(bowlingGame, bowlingGames);
        }
    }

    private void playByPlayer(final BowlingGame bowlingGame, final BowlingGames bowlingGames) {
        boolean isFrameEnd = false;
        while (!isFrameEnd) {
            int countOfPins = InputView.inputPlayerTurn(bowlingGame);
            isFrameEnd = bowlingGame.pitch(countOfPins);
            ResultView.printResult(bowlingGames);
        }
    }

}
