package bowling.domain.frame;

import bowling.domain.core.RolledResult;
import bowling.ui.result.DisplayRolledResult;

interface Frame {
    void updateRolledResult(RolledResult rolledResult);
    default void updateScore(RolledResult nextRolledResult) {}
    int getScore();
    default RolledResult getRolledResult() {throw new UnsupportedOperationException(); }
    DisplayRolledResult toDisplayRolledResult();
    int increaseNextStepFrame();
}
