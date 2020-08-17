package bowling.domian.frame;

public interface Frame {
    int getFrameNumber();

    Frame bowl(int falledPinsCount);

    boolean isGameEnd();
}
