package bowling.domain;

public interface Frame {
    Frame throwBall(int fallingPins);

    Scoring getScoring();

    int getNumber();

    FrameStatus getFrameStatus();

    default boolean isFinish() {
        return false;
    }
}
