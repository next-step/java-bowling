package bowling.domain.frame;

import bowling.domain.score.Pin;
import bowling.domain.score.Score;

public interface Frame {

    int round();

    Frame nextFrame();

    Frame bowling(Pin pin);

    FrameResult createFrameResult();

    Score calculateAdditionalScore(Score score);

    boolean isFinished();

}
