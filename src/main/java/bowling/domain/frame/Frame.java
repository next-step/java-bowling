package bowling.domain.frame;

import bowling.domain.core.RolledResult;
import bowling.ui.result.DisplayRolledResult;

interface Frame {
    default void updateRolledResult(int fallenPins){}
    int getScore();
    default RolledResult getRolledResult() {throw new UnsupportedOperationException(); }
    DisplayRolledResult toDisplayRolledResult();
    int increaseNextStepFrame();
    void score(Frame prevFrame, Frame nextFrame);
}
