package bowling.refactoring.domain.frame;

import bowling.refactoring.domain.*;
import bowling.refactoring.domain.state.*;

public abstract class Frame {
    abstract void bowl(int pins);

    abstract boolean isEnd();

    abstract boolean isEndedCalculateScore();

    abstract void calculateBonusScore(Frame nextFrame);

    public abstract Score score();

    public abstract State state();
}
