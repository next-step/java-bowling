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

    boolean isFirstPitchingClear();

    boolean isSecondPitchingClear();

    boolean isSpare();

    int getFirstScore();

    int calculateScore();
}
