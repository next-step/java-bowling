package bowling.domain.frame;

import bowling.domain.score.Pin;

public interface Frame {

    boolean isFinishState();

    void updateStateByPin(Pin pin);

    Frame createNextFrame();

}
