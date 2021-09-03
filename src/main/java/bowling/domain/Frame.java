package bowling.domain;

public interface Frame {

    Frame bowl(Pins pins);

    Pins getFirstPin();

    Pins getSecondPin();

    boolean isFinish();

    int getFrameNumber();
}
