package bowling.domain.frame;

import bowling.domain.core.RolledResult;
import bowling.ui.result.DisplayRolledResult;

interface Frame {
    void updateRolledResult(RolledResult rolledResult);
    void updateScore(RolledResult nextRolledResult);
    int getScore();
    RolledResult getRolledResult();
    DisplayRolledResult toDisplayRolledResult();
    int increaseNextStepFrame();
}
