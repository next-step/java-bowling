package bowling.domain.frame;

import bowling.domain.FrameResult;
import bowling.domain.PinCount;
import bowling.domain.Score;

public interface Frame {
    Frame play(PinCount pinCount);

    Score calculateScore();

    Score plusBonusScore(Score score);

    FrameNumber getFrameNumber();

    FrameResult makeResult();
}
