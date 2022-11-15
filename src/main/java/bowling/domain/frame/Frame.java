package bowling.domain.frame;

import bowling.domain.state.HitState;

public interface Frame {

    int MIN_NUMBER_OF_BOWLING_PINS = 0;
    int MAX_NUMBER_OF_BOWLING_PINS = 10;

    void add(int hit);

    int countHits();

    int getHit(int index);

    HitState getState(int index);

    default boolean isOnGoing() {
        return !isEnded();
    }

    boolean isEnded();

    int getRemainedPins();

    Frame getNextFrame();

    boolean hasScore();

    int getScore();

}
