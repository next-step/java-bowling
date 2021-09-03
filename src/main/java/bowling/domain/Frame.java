package bowling.domain;

public interface Frame {

    void bowl(Pins pins);

    Pins getFirstPin();

    Pins getSecondPin();

    Pins getBonusPin();

    boolean isFirstFitchDone();

    boolean isSecondFitchDone();

    boolean isBonusFitchDone();

    boolean isFinish();

    int getFrameNumber();

    Frame next();
}
