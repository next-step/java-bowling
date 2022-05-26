package bowling;

import bowling.domain.BowlingGame;
import bowling.domain.PlayerName;
import bowling.frame.*;
import bowling.score.ScoreBoard;
import bowling.score.Scores;
import bowling.view.InputView;
import bowling.view.ResultView;

public class BowlingApplication {

    public static void main(String[] args) {
        InputView inputView = new InputView();
        ResultView resultView = new ResultView();

        Frames frames = Frames.create();
        Scores scores = Scores.create();
        ScoreBoard scoreBoard = ScoreBoard.create();
        PlayerName playerName = PlayerName.from(inputView.inputPlayerName());
        BowlingGame bowlingGame = BowlingGame.from(playerName, frames, scores, scoreBoard);

        startBowlingGame(bowlingGame, inputView, resultView);
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

}
