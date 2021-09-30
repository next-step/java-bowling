package bowling.domain.frame;

import bowling.domain.score.Pin;
import bowling.domain.score.Score;

public interface Frame {

    Frame lastFrame();

    Score score();

    boolean isScoreNextStorable();

    void updateScorePin(Pin pin);

    Frame createNextFrame();

    Frame nextFrame();

    int round();

}
