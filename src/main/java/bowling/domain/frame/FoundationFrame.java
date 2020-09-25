package bowling.domain.frame;

import bowling.domain.core.RolledResult;
import bowling.domain.core.state.Score;
import bowling.ui.result.DisplayRolledResult;

import static bowling.domain.core.state.NotAtRolledResult.notAtRolledResult;

final class FoundationFrame implements Frame {
    private RolledResult rolledResult;
    private LinkedScoreCalculator linkedScoreCalculator;

    FoundationFrame() {
        this.rolledResult = notAtRolledResult();
    }

    @Override
    public void link(Frame prevFrame, Frame nextFrame) {
        linkedScoreCalculator = new LinkedScoreCalculator(prevFrame, nextFrame);
    }

    @Override
    public void updateRolledResult(int fallenPins) {
        this.rolledResult = rolledResult.nextRolledResult(fallenPins);
    }

    @Override
    public int getScore() {
        return linkedScoreCalculator.getScore(rolledResult);
    }

    @Override
    public DisplayRolledResult toDisplayRolledResult() {
        if (rolledResult.isCompleteState()) {
            return new DisplayRolledResult(rolledResult.description(), getScore());
        }

        return DisplayRolledResult.ofNotCalculated(rolledResult.description());
    }

    @Override
    public int increaseNextStepFrame() {
        return rolledResult.isCompleteState() ? 1 : 0;
    }

    @Override
    public Score calculateScore(Score score) {
        return linkedScoreCalculator.calculateScore(rolledResult, score);
    }
}
