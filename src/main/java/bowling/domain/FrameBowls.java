package bowling.domain;

public interface FrameBowls {
    FrameBowls firstThrow(int firstPinCount);

    FrameBowls secondThrow(int secondPinCount);

    boolean isFirstBowlStrike();

    boolean isSecondBowlSpare();

    int totalPinCount();

    PinCount firstPinCount();

    PinCount secondPinCount();

    boolean isFirstBowlThrown();

    boolean isSecondBowlThrown();
}
