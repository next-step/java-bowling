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
            int currentFrameNumber = bowlingGame.currentFrameNumber();
            int pintCount = InputView.pinCount(currentFrameNumber);
            bowlingGame.play(pintCount);
            printScoreBoard(bowlingGame.scoreBoard());
        }
    }

    private static void printScoreBoard(ScoreBoard scoreBoard) {
        ResultView.printScoreBoardHeader(scoreBoard.totalNumberOfFrame());

        PlayResult playResult = scoreBoard.playResult();
        printFrames(playResult);
        printScores(playResult.allScores());
        ResultView.printEmptyLine();
    }

    private static void printFrames(PlayResult playResult) {
        ResultView.printFrameStates(playResult);
    }

    private static void printScores(List<ScoreDto> scores) {
        int accumulatesScore = 0;
        ResultView.printScoreHeader();
        for (ScoreDto score : scores) {
            accumulatesScore += score.currentScore();
            printSingleScore(score.isFullyCalculated(), accumulatesScore);
        }
        ResultView.printEmptyLine();
    }

    private static void printSingleScore(boolean isFullyCalculated, int accumulatesScore) {
        if (isFullyCalculated) {
            ResultView.printScore(accumulatesScore);
        } else {
            ResultView.printEmptyScore();
        }
    }
}
