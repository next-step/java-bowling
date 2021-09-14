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
            turnPlayer(bowlingGames);
        }
    }

    private void turnPlayer(BowlingGames bowlingGames) {
        for (BowlingGame bowlingGame : bowlingGames.games()) {
            boolean flag = false;
            while (!flag) {
                int countOfPins = InputView.inputPlayerTurn(bowlingGame);
                flag = bowlingGame.pitch(countOfPins);
                ResultView.printResult(bowlingGames);
            }
        }
    }


}
