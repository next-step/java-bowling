package bowling.domain.frame;

public interface Frame {
    void bowl(int falledPins);

    Frame createNextFrame();

    int getFrameNumber();

    boolean isFinish();
}
