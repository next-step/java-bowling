package bowling.controller;

import bowling.domain.BowlingGame;
import bowling.domain.Pin;
import bowling.domain.PlayerName;
import bowling.domain.scorestrategy.RandomScoreStrategy;
import bowling.view.InputView;
import bowling.view.OutputView;

public class BowlingGameController {

    public static void main(String[] args) {
        PlayerName playerName = new PlayerName(InputView.askPlayerName());
        BowlingGame bowlingGame = new BowlingGame();
        OutputView.printStart(bowlingGame, playerName);

        while (!bowlingGame.isFinished()) {
            Pin now = bowlingGame.bowl(new RandomScoreStrategy());
            OutputView.print(bowlingGame, playerName, now);
        }

    }
}
