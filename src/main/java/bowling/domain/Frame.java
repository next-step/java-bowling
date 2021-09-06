package bowling.domain;

public interface Frame {

    Frame roll(int downPins);

    int round();

    Pins pins();

}
