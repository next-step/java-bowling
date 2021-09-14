package bowling.controller;

import bowling.domain.frame.AllFrames;
import bowling.domain.player.Player;
import bowling.domain.player.Players;
import bowling.domain.score.PureScores;
import bowling.domain.score.Score;
import bowling.domain.score.TotalScoreBoard;
import bowling.view.InputView;
import bowling.view.ResultView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class BowlingGameController {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        ResultView resultView = new ResultView();
        List<String> playerNames = inputView.inputPlayerNames();

        AllFrames allFrames = AllFrames.from(new ArrayList<>(), playerNames.size());
        Players players = Players.init(playerNames, allFrames);

        resultView.outputScores(players, allFrames);
        executeGame(players, allFrames);
        inputView.scannerClose();
    }

    private static void executeGame(Players players, AllFrames allFrames) {
        while (!allFrames.isGameFinish()) {
            IntStream.range(0, players.numberOfPlayers())
                .forEach(playerNo -> loopInputAndOutput(players, allFrames, playerNo));
        }
    }

    private static void loopInputAndOutput(Players players, AllFrames allFrames, int playerNo) {
        InputView inputView = new InputView();
        ResultView resultView = new ResultView();
        Player player = players.nthOf(playerNo);
        TotalScoreBoard totalScoreBoard = players.totalScoreBoard();
        boolean isNext = false;
        while (!isNext) {
            List<Integer> cumulatedScores = totalScoreBoard.nthCumulativeScoresOf(playerNo);
            PureScores pureScores = totalScoreBoard.nthPureScoresOf(playerNo);
            int downPinNumber = inputView.inputPlayerTurnPinNumber(player.name());
            pureScores.addScore(Score.from(downPinNumber));
            allFrames.throwBallOfNthPlayer(playerNo, downPinNumber);
            totalScoreBoard.updateNthCumulativeScoresOf(playerNo, pureScores.getCumulativeScores(allFrames.nthFramesOf(playerNo), cumulatedScores));
            resultView.outputScores(players, allFrames);
            isNext = allFrames.isNextOfNthPlayer(playerNo);
        }
    }
}
