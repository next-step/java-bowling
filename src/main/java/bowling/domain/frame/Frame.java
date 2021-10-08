package bowling.domain.frame;

import bowling.domain.score.Pin;
import bowling.domain.score.Score;

public interface Frame {

    Frame bowling(Pin pin);

    FrameResult createFrameResult();

    Score calculateAdditionalScore(Score score);

}
