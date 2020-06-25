package bowling.domain;

public interface Frame {
    void bowl(Pin pin);
    boolean isEndFrame();
    boolean isEndGame();
    Frame getNextFrame(int frameNumber);
}
