package bowling;

import bowling.domain.Name;
import bowling.domain.Player;
import bowling.domain.ScoreBoard;
import bowling.domain.score.TurnScore;
import bowling.view.DosInputView;
import bowling.view.DosResultView;
import bowling.view.InputView;
import bowling.view.ResultView;

import java.util.Collections;

public class BowlingSolution {
    public static void main(String[] args) {
        new BowlingSolution(
                new DosInputView(), new DosResultView()
        ).run();
    }

    private final InputView inputView;
    private final ResultView resultView;

    protected BowlingSolution(final InputView inputView, final ResultView resultView) {
        this.inputView = inputView;
        this.resultView = resultView;
    }

    protected void run() {
        try {
            realRun();
        } catch (Exception e) {
            resultView.printException(e);
        }
    }

    private void realRun() {
        Name name = inputName();

        ScoreBoard scoreBoard = startRecord(name);
        resultView.printScoreBoard(scoreBoard);

        while(!scoreBoard.isCompleted()) {
            recordScore(scoreBoard);
            resultView.printScoreBoard(scoreBoard);
        }
    }

    private Name inputName() {
        return new Name(inputView.inputName());
    }

    private ScoreBoard startRecord(Name name) {
        return ScoreBoard.generate(
                Collections.singletonList(name)
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
