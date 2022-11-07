package bowling.domain;

public interface BowlingGameFrame {

    int MIN_NUMBER_OF_BOWLING_PINS = 0;
    int MAX_NUMBER_OF_BOWLING_PINS = 10;

    void add(int hit);

    int size();

    int get(int index);

    boolean isStrike();

    boolean isSpare();

    boolean isMiss();

    default boolean isOnGoing() {
        return !isEnded();
    }

    boolean isEnded();

    int getRemainedPins();

}
