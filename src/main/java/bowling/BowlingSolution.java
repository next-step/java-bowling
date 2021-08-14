package bowling;

import bowling.domain.player.Player;
import bowling.domain.player.PlayerName;
import bowling.domain.score.TurnScore;
import bowling.domain.scoreboard.RotationPlayerOrderStrategy;
import bowling.domain.scoreboard.ScoreBoard;
import bowling.view.DosInputView;
import bowling.view.DosResultView;
import bowling.view.InputView;
import bowling.view.ResultView;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BowlingSolution {
    public static void main(String[] args) {
        try {
            new BowlingSolution(new DosInputView(), new DosResultView()).run();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private final InputView inputView;
    private final ResultView resultView;

    protected BowlingSolution(final InputView inputView, final ResultView resultView) {
        this.inputView = inputView;
        this.resultView = resultView;
    }

    public void run() {
        List<PlayerName> playerNames = inputNames();

        ScoreBoard scoreBoard = startRecord(playerNames);
        resultView.printScoreBoard(scoreBoard);

        while(!scoreBoard.isCompleted()) {
            recordScore(scoreBoard);
            resultView.printScoreBoard(scoreBoard);
        }
    }

    private List<PlayerName> inputNames() {
        int playerSize = inputView.inputPlayerSize();

        return Stream.generate(inputView::inputName)
                        .limit(playerSize)
                        .map(PlayerName::new)
                        .collect(Collectors.toList());
    }

    private ScoreBoard startRecord(List<PlayerName> playerNames) {
        return ScoreBoard.generate(
                playerNames, RotationPlayerOrderStrategy::new
        );
    }

    private void recordScore(ScoreBoard scoreBoard) {
        Player currentPlayer = scoreBoard.currentPlayer();
        TurnScore score = inputTurnScore(currentPlayer);

        scoreBoard.record(currentPlayer, score);
    }

    private TurnScore inputTurnScore(Player currentPlayer) {
        return TurnScore.of(
                inputView.inputTurnScore(currentPlayer.name().value())
        );
    }
}
