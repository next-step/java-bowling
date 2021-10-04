package bowling.domain.frame;

import bowling.domain.score.Pin;

public interface Frame {

    boolean isFinishState();

    void bowling(Pin pin);

    Frame createNextFrame();

    Frame nextFrame();

    int round();

}
