package bowling.domain.frame;

public interface Frame {
    void bowl(int falledPins);

    int getFrameNumber();

    boolean isFinish();
}
