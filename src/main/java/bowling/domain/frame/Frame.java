package bowling.domain.frame;

import bowling.domain.score.Pin;
import bowling.domain.score.Score;

public interface Frame {

    Frame bowling(Pin pin);

    Frame createNextFrame();

    FrameResult createFrameResult();

    Score calculateAdditionalScore(Score score);

}
