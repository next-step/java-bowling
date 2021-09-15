package bowling;

import bowling.domain.*;
import bowling.view.InputView;
import bowling.view.OutputView;

import java.util.List;
import java.util.stream.IntStream;

public class Game {

    public static void main(String[] args) {
        List<String> names = InputView.inputPlayer();
        TotalFrames totalFrame = new TotalFrames(names.size());
        Players players = new Players(names, totalFrame);

        OutputView.outputScores(players, totalFrame);

        startGame(players, totalFrame);
    }

    private static void startGame(Players players, TotalFrames totalFrames) {
        while (!totalFrames.isGameFinish()) {
            IntStream.range(0, players.numberOfPlayers())
                     .forEach(playerIndex -> execute(players, totalFrames, playerIndex));
        }
    }

    private static void execute(Players players, TotalFrames totalFrames, int playerIndex) {
        Player player = players.of(playerIndex);
        ScoreBoard scoreBoard = players.scoreBoard();
        boolean isNext = false;
        while (!isNext) {
            List<Integer> calculatedScores = scoreBoard.calculatedScoresOf(playerIndex);
            TotalScores totalScores = scoreBoard.totalScoreOf(playerIndex);
            int pitchingCount = InputView.inputPitchingCount(player.name());

            totalScores.addScore(Score.valueOf(pitchingCount));
            totalFrames.throwBall(playerIndex, pitchingCount);
            scoreBoard.updateCalculatedScoresOf(playerIndex, totalScores.getTotalScores(totalFrames.of(playerIndex), calculatedScores));
            OutputView.outputScores(players, totalFrames);
            isNext = totalFrames.isNextPlayer(playerIndex);
        }
    }
}
