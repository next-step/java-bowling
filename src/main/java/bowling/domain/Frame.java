package bowling.domain;

public interface Frame {

    void bowl(Pins pins);

    Pins getFirstPin();

    Pins getSecondPin();

    boolean isFirstFitchDone();

    boolean isSecondFitchDone();

    boolean isFinish();

    int getFrameNumber();

    Frame next();
}
