package bowling.domain.frame;

import bowling.domain.core.RolledResult;
import bowling.domain.core.state.Score;
import bowling.ui.result.DisplayRolledResult;

import static bowling.domain.core.state.NotAtRolledResult.notAtRolledResult;
import static bowling.domain.core.state.Spare.expectSpareAfterBonusBowl;

public final class TerminateFrame implements Frame {
    public static final int MAX_TRY_COUNT_SIZE = 3;

    private RolledResult currentRolledResult = notAtRolledResult();
    private TerminateRolledResults terminateRolledResults;
    private Frame prevFrame;

    TerminateFrame() {
        terminateRolledResults = new TerminateRolledResults();
    }

    @Override
    public void link(Frame prevFrame, Frame nextFrame) {
        this.prevFrame = prevFrame;
    }

    @Override
    public void updateRolledResult(int fallenPins) {
        RolledResult nextRolledResult = currentRolledResult.nextRolledResult(fallenPins);
        if (nextRolledResult.isCompleteState()) {
            terminateRolledResults.add(nextRolledResult);
            this.currentRolledResult = notAtRolledResult();
        }
        this.currentRolledResult = expectSpareAfterBonusBowl(nextRolledResult);
    }

    @Override
    public int getScore() {
        return prevFrame.getScore()
            + terminateRolledResults.getScore();
    }

    @Override
    public Score calculateScore(Score score) {
        return terminateRolledResults.calculateScore(score);
    }

    @Override
    public DisplayRolledResult toDisplayRolledResult() {
        String description = currentRolledResult.description();

        if(terminateRolledResults.isNotEmpty()) {
            description = currentRolledResult.isNotCompleteState()
                ? String.join("|", terminateRolledResults.description(), currentRolledResult.description())
                : terminateRolledResults.description();
            return new DisplayRolledResult(description, getScore());
        }

        return DisplayRolledResult.ofNotCalculated(description);
    }

    @Override
    public int increaseNextStepFrame() {
        return terminateRolledResults.increaseNextStepFrame();
    }
}
