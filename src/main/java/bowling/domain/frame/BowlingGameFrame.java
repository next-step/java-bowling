package bowling.domain.frame;

import bowling.domain.state.BowlingGameHitState;

public interface BowlingGameFrame {

    int MIN_NUMBER_OF_BOWLING_PINS = 0;
    int MAX_NUMBER_OF_BOWLING_PINS = 10;

    void add(int hit);

    int countHits();

    int getHit(int index);

    BowlingGameHitState getState(int index);

    default boolean isOnGoing() {
        return !isEnded();
    }

    boolean isEnded();

    int getRemainedPins();

    BowlingGameFrame getNextFrame();

    boolean hasScore();

    int getScore();

}
