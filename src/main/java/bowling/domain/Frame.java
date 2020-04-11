package bowling.domain;

public interface Frame {
    void addPinCount(PinCount nextPinCount);
    int getScore();
    boolean isDone();

}
