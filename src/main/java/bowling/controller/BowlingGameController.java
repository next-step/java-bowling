package bowling.controller;

import bowling.domain.BowlingGames;
import bowling.view.InputView;
import bowling.view.ResultView;

public class BowlingGameController {
    public void startGame() {
        int playCount = InputView.inputPlayerCount();
        BowlingGames bowlingGames = new BowlingGames(InputView.inputPlayerName(playCount));

        ResultView.printInit(bowlingGames);
        while (!bowlingGames.isEnd()) {
            int countOfPins = InputView.inputPlayerTurn(bowlingGames.currentPlayerFrames());
            bowlingGames.pitch(countOfPins);
            ResultView.printResult(bowlingGames);
        }
    }
}
