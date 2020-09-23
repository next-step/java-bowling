package bowling.domain.frame;

import bowling.domain.core.state.Score;
import bowling.ui.result.DisplayRolledResult;

interface Frame {
    default void updateRolledResult(int fallenPins){}
    DisplayRolledResult toDisplayRolledResult();
    int increaseNextStepFrame();
    int getScore();
    void link(Frame prevFrame, Frame nextFrame);
    default Score calculateScore(Score score) { return null; }
}
