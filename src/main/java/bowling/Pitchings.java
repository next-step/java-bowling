package bowling;

public interface Pitchings {

    void bowl(Pin pin);

    Pin getFirstPin();

    Pin getSecondPin();

    default Pin getBonusPin() {
        return Pin.ofMin();
    }

    boolean isFirstDone();

    boolean isSecondDone();

    default boolean isBonusDone() {
        return Boolean.TRUE;
    }

    boolean isDone();

    boolean isStrike();

    boolean isSpare();

    int calculateScore();

    int giveStrikeBonusScore();

    int giveSpareBonusScore();

    default int giveBonusScore() {
        return 0;
    }
}
