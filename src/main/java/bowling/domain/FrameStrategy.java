package bowling.domain;

public interface FrameStrategy {
    int size();
    void play(PinNumber pinNumber);
    String state(int index);
}
