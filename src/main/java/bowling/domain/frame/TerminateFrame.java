package bowling.domain.frame;

import bowling.domain.core.RolledResult;
import bowling.ui.result.DisplayRolledResult;

import static bowling.domain.core.state.NotAtRolledResult.notAtRolledResult;
import static bowling.domain.core.state.Spare.expectSpareAfterBonusBowl;

public final class TerminateFrame implements Frame {
    public static final int MAX_TRY_COUNT_SIZE = 3;

    private RolledResult currentRolledResult = notAtRolledResult();
    private TerminateRolledResult terminateRolledResult;
    private Frame prevFrame;

    TerminateFrame() {
        terminateRolledResult = new TerminateRolledResult();
    }

    @Override
    public void score(Frame prevFrame, Frame nextFrame) {
        this.prevFrame = prevFrame;
    }

    @Override
    public RolledResult getRolledResult() {
        return currentRolledResult;
    }

    @Override
    public void updateRolledResult(int fallenPins) {
        RolledResult nextRolledResult = currentRolledResult.nextRolledResult(fallenPins);
        if (nextRolledResult.isCompleteState()) {
            terminateRolledResult.add(nextRolledResult);
            this.currentRolledResult = notAtRolledResult();
        }
        this.currentRolledResult = expectSpareAfterBonusBowl(nextRolledResult);
    }

    @Override
    public int getScore() {
        return prevFrame.getScore() + terminateRolledResult.totalScore();
    }

    @Override
    public DisplayRolledResult toDisplayRolledResult() {
        return new DisplayRolledResult(terminateRolledResult.description(), getScore());
    }

    @Override
    public int increaseNextStepFrame() {
        return terminateRolledResult.increaseNextStepFrame();
    }
}
