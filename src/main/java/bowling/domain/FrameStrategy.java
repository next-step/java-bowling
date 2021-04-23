package bowling.domain;

public interface FrameStrategy {
    int size();
    void play(PinNumber pinNumber);
    String result(int index);
    boolean hasNext();
    FrameStrategy nextFrame(int frameNumber);
}
