package bowling.ui;

import bowling.domain.BowlingGame;
import bowling.domain.BowlingGameResult;

import java.util.List;

public class FirstFrameControllerRefactor {
    private BowlingGame bowlingGame;

    public FirstFrameControllerRefactor(BowlingGame bowlingGame) {
        this.bowlingGame = bowlingGame;
    }

    public void doFirstFrame() {
        List<BowlingGameResult> firstBowlResult = bowlingGame.bowlFirst(InputView.getNumberOfHitPin(1));
        OutputView.printBowlingGameResult(bowlingGame.getPlayerName(), firstBowlResult);
        doNotCompletedFirstFrame();
    }

    private void doNotCompletedFirstFrame() {
        while(!bowlingGame.isCurrentFrameCompleted()) {
            List<BowlingGameResult> bowlingGameResults
                    = bowlingGame.bowlCurrentFrame(InputView.getNumberOfHitPin(1));
            OutputView.printBowlingGameResult(bowlingGame.getPlayerName(), bowlingGameResults);
        }
    }
}
