package bowling.domain;

public abstract class Frame {
    public static final int START_FRAME_NUMBER = 1;
    public static final int FINAL_FRAME_NUMBER = 10;
    protected int round;

    protected Balls balls;

    public Frame() {
    }

    public abstract void play(int knockedDownPinCount);

    public abstract String scoringText();

    public abstract Frame createNextFrame();

    public String scoringTextNormalFrame() {
        return balls.scoringText();
    }

    public int getRound() {
        return round;
    }

    public boolean isEnd() {
        return balls.pitchEnd();
    }
}
