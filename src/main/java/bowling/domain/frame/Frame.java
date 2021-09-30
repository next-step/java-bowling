package bowling.domain.frame;

import bowling.domain.score.Pin;
import bowling.domain.score.Score;

public interface Frame {

    // TODO : finalFrame exception
    Frame lastFrame();

    Score score();

    boolean isScoreNextStorable();

    void updateScorePin(Pin pin);

    Frame createNextFrame();

    Frame nextFrame();

    // TODO : finalFrame exception
    int round();

}
