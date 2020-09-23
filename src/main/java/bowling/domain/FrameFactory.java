package bowling.domain;

public class FrameFactory {

    private static final int BEFORE_FINAL_FRAME = 9;

    public static NormalFrame first() {
        return new NormalFrame();
    }

    public static Frame next(Frame frame, int currentRound) {

        if (currentRound == BEFORE_FINAL_FRAME) {
            return new FinalFrame();
        }

        return new NormalFrame();
    }

    public static FinalFrame last() {
        return new FinalFrame();
    }
}
