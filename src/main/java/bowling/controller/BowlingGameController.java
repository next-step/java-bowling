package bowling.controller;

import java.util.List;
import java.util.stream.IntStream;

import bowling.domain.player.Player;
import bowling.domain.player.Players;
import bowling.domain.score.PureScores;
import bowling.domain.score.Score;
import bowling.domain.score.TotalScoreBoard;
import bowling.view.InputView;
import bowling.view.ResultView;

public class BowlingGameController {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        ResultView resultView = new ResultView();
        Players players = Players.init(inputView.inputPlayerNames());
        resultView.outputScores(players);
        executeGame(players);
        inputView.scannerClose();
    }

    private static void executeGame(Players players) {
        while (!players.isGameFinish()) {
            IntStream.range(0, players.numberOfPlayers())
                .forEach(playerNo -> loopInputAndOutput(players, playerNo));
        }
    }

    private static void loopInputAndOutput(Players players, int playerNo) {
        InputView inputView = new InputView();
        ResultView resultView = new ResultView();
        Player player = players.players().get(playerNo);
        TotalScoreBoard totalScoreBoard = players.totalScoreBoard();
        boolean isNext = false;
        while (!isNext) {
            List<Integer> cumulatedScores = totalScoreBoard.cumulativeScoresOfPlayers().get(playerNo);
            PureScores pureScores = totalScoreBoard.pureScoresOfPlayers().get(playerNo);
            int downPinNumber = inputView.inputPlayerTurnPinNumber(player.name());
            pureScores.addScore(Score.from(downPinNumber));
            player.frames().throwBalls(downPinNumber);
            totalScoreBoard.cumulativeScoresOfPlayers()
                .set(playerNo, pureScores.getCumulativeScores(player.frames(), cumulatedScores));
            resultView.outputScores(players);
            isNext = player.frames().isNext();
        }
    }
}
