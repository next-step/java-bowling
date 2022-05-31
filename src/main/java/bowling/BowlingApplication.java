package bowling;

import bowling.domain.Lane;
import bowling.domain.BowlingGame;
import bowling.domain.PlayerName;
import bowling.frame.*;
import bowling.view.InputView;
import bowling.view.ResultView;

import java.util.List;

public class BowlingApplication {

    public static void main(String[] args) {
        InputView inputView = new InputView();
        ResultView resultView = new ResultView();

        List<PlayerName> playerNames = inputView.inputPlayerName();
        Lane lane = Lane.from(playerNames);

        startBowlingGame(lane, inputView, resultView);
    }

    private static void startBowlingGame(BowlingGame bowlingGame, InputView inputView, ResultView resultView) {
        resultView.printFrameBoard(bowlingGame);

        while (!bowlingGame.isEnd()) {
            int currentRound = bowlingGame.currentRound();
            int shootScore = inputView.inputShootScore(Round.from(currentRound));
            bowlingGame.shoot(ShootScore.from(shootScore));
            resultView.printFrameBoard(bowlingGame);
        }
    }

    private static void startBowlingGame(Lane lane, InputView inputView, ResultView resultView) {
        resultView.printFrameBoard(lane);

        while (!lane.isEnd()) {
            String currentPlayer = lane.currentPlayer();
            int shootScore = inputView.inputShootScore(currentPlayer);

            lane.shoot(ShootScore.from(shootScore));
            resultView.printFrameBoard(lane);
        }
    }

}
