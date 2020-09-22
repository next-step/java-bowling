package bowling.domain.frame;

import java.util.Objects;

import bowling.domain.core.RolledResult;
import bowling.ui.result.DisplayRolledResult;

import static bowling.domain.core.state.NotAtRolledResult.notAtRolledResult;

final class FoundationFrame implements Frame {
    private RolledResult rolledResult;
    private Score score;

    FoundationFrame() {
        this.rolledResult = notAtRolledResult();
    }

    @Override
    public void score(Frame prevFrame, Frame nextFrame) {
        score = new Score(prevFrame, nextFrame);
    }

    @Override
    public void updateRolledResult(int fallenPins) {
        this.rolledResult = rolledResult.nextRolledResult(fallenPins);
    }

    @Override
    public int getScore() {
        return score.getScore(rolledResult);
    }

    @Override
    public RolledResult getRolledResult() {
        return rolledResult;
    }

    @Override
    public DisplayRolledResult toDisplayRolledResult() {
        return new DisplayRolledResult(rolledResult.description(), getScore());
    }

    @Override
    public int increaseNextStepFrame() {
        return rolledResult.isCompleteState() ? 1 : 0;
    }

}
