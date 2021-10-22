package bowling.domain.frame;

import bowling.domain.score.Pin;
import bowling.domain.score.Score;
import java.util.Optional;

public interface Frame {

    int round();

    Optional<Frame> nextFrame();

    Frame bowling(Pin pin);

    FrameResult createFrameResult();

    Score calculateAdditionalScore(Score score);

}
