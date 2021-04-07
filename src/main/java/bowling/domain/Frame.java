package bowling.domain;

public interface Frame {

    void addPinCount(int pinCount);

    void addPinCount(PinCount pinCount);

    void boolean isDone();

    Frame nextFrame();

    FrameNumber number();

    boolean isLast();
}
