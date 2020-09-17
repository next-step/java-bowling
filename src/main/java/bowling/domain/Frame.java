package bowling.domain;

public interface Frame extends Iterable<Frame> {

    Frame bowl(int numberOfPins);
    Frame getNextFrame();
    int getFrameNumber();

}
