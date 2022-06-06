package bowling.domain.pin;

import bowling.exception.NotSupportMethodException;

public interface Pins {

    int FIRST_HIT_INDEX = 0;
    int SECOND_HIT_INDEX = 1;
    int THIRD_HIT_INDEX = 2;
    int MAX_HITS_SIZE = 2;
    int ONE_HIT = 1;

    void fallDown(int hit, boolean isBonusHit);

    int firstHit();
    int secondHit();
    default int thirdHit() {
        throw new NotSupportMethodException();
    }
    int totalHits();
    int hitSize();
    boolean hasSecondHit();
    default boolean hasThirdHit() {
        throw new NotSupportMethodException();
    }
}
