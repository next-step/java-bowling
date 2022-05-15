package bowling.model.frame;

import bowling.model.Pins;

public interface BallState {

    boolean isEnd();

    BallState state(Pins countOfHit);

    int remainCount();
}
