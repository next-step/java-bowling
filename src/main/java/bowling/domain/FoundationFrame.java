package bowling.domain;

import bowling.domain.core.RolledResult;
import bowling.ui.result.DisplayRolledResult;

import static bowling.domain.core.RolledResultFactory.notAtRolledResult;

final class FoundationFrame implements Frame {
    private RolledResult rolledResult;
    private int score;

    FoundationFrame() {
        this.rolledResult = notAtRolledResult();
        score = 0;
    }

    @Override
    public void updateRolledResult(RolledResult rolledResult) {
        this.rolledResult = rolledResult;
        updateScoreForMiss(rolledResult);
    }

    @Override
    public void updateScore(RolledResult nextRolledResult) {
        updateScoreForStrikeOrSpare(nextRolledResult);
    }

    private void updateScoreForMiss(RolledResult rolledResult) {
        if (this.rolledResult.isCompleteState()){
            score = rolledResult.getRolledResultScore();
        }
    }

    private void updateScoreForStrikeOrSpare(RolledResult nextRolledResult) {
        if (rolledResult.isCompleteState() && nextRolledResult.isCompleteState()) {
            score = rolledResult.getNextRolledResultMergeScore(nextRolledResult);
        }
    }

    @Override
    public int getScore() {
        return score;
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
