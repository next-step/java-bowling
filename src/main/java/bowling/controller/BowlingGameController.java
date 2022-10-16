package bowling.controller;

import bowling.domain.BowlingGame;
import bowling.domain.PlayerName;
import bowling.domain.scorestrategy.RandomScoreStrategy;
import bowling.view.InputView;
import bowling.view.OutputView;

public class BowlingGameController {

    public static void main(String[] args) {
        String playerName = InputView.askPlayerName();
        BowlingGame bowlingGame = new BowlingGame(new PlayerName(playerName));
        OutputView.printStart(bowlingGame);
        while (!bowlingGame.isFinished()) {
            bowlingGame.bowl(new RandomScoreStrategy());
            OutputView.print(bowlingGame);
        }
    }
}
