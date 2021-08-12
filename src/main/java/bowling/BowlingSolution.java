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

import java.util.Collections;

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
        PlayerName name = inputName();

        ScoreBoard scoreBoard = startRecord(name);
        resultView.printScoreBoard(scoreBoard);

        while(!scoreBoard.isCompleted()) {
            recordScore(scoreBoard);
            resultView.printScoreBoard(scoreBoard);
        }
    }

    private PlayerName inputName() {
        return new PlayerName(inputView.inputName());
    }

    private ScoreBoard startRecord(PlayerName name) {
        return ScoreBoard.generate(
                Collections.singletonList(name), new RotationPlayerOrderStrategy()
        );
    }

    private void recordScore(ScoreBoard scoreBoard) {
        Player currentPlayer = scoreBoard.currentPlayer();
        TurnScore score = inputTurnScore(scoreBoard, currentPlayer);

        scoreBoard.record(currentPlayer, score);
    }

    private TurnScore inputTurnScore(ScoreBoard scoreBoard, Player currentPlayer) {
        return TurnScore.of(
                inputView.inputTurnScore(
                        scoreBoard.currentFrameNumber(currentPlayer)
                )
        );
    }
}
