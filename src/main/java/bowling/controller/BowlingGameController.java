package bowling.controller;

import bowling.domain.BowlingGame;
import bowling.dto.PlayResult;
import bowling.dto.ScoreBoard;
import bowling.dto.ScoreDto;
import bowling.view.InputView;
import bowling.view.ResultView;

import java.util.List;

public class BowlingGameController {

    public static void run() {
        BowlingGame bowlingGame = new BowlingGame(InputView.bowlingGameRequest());
        printScoreBoard(bowlingGame.scoreBoard());
        while (!bowlingGame.isDone()) {
            String playingPlayer = bowlingGame.playingPlayer();
            int pintCount = InputView.pinCount(playingPlayer);
            bowlingGame.play(pintCount);
            printScoreBoard(bowlingGame.scoreBoard());
        }
    }

    private static void printScoreBoard(ScoreBoard scoreBoard) {
        ResultView.printScoreBoardHeader(scoreBoard.totalNumberOfFrame());
        scoreBoard.playResults()
                .forEach(BowlingGameController::printPersonalPlayResult);
    }

    private static void printPersonalPlayResult(PlayResult playResult) {
        printFrames(playResult);
        printScores(playResult.allScores());
        ResultView.printEmptyLine();
    }

    private static void printFrames(PlayResult playResult) {
        ResultView.printFrameStates(playResult);
    }

    private static void printScores(List<ScoreDto> scores) {
        int accumulatedScore = 0;
        ResultView.printScoreHeader();
        for (ScoreDto score : scores) {
            accumulatedScore += score.currentScore();
            printSingleScore(score.isFullyCalculated(), accumulatedScore);
        }
        ResultView.printEmptyLine();
    }

    private static void printSingleScore(boolean isFullyCalculated, int accumulatesScore) {
        if (isFullyCalculated) {
            ResultView.printScore(accumulatesScore);
            return;
        }
        ResultView.printEmptyScore();
    }
}
