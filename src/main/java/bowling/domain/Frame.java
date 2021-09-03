package bowling.domain;

public interface Frame {

    void bowl(Pins pins);

    Pins getFirstPin();

    Pins getSecondPin();

    boolean isFinish();
}
