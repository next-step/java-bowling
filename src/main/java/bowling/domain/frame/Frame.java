package bowling.domain.frame;

import bowling.domain.Content;
import bowling.domain.Score;
import bowling.exception.NotCreateFrameException;

public interface Frame {

    Frame next() throws NotCreateFrameException;
    void bowling(int hit);
    Score calculate(Score score);
    boolean isFinish();
    boolean hasLastBonusChance();
    Content content();
    Score score();
}
