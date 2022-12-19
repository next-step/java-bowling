package bowling.model.frame;

import bowling.model.Pin;

public interface Frame {

    void bowl(Pin pin);
    boolean isFinished();

}
