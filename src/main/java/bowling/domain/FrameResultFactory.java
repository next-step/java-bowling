package bowling.domain;

public class FrameResultFactory {
    private static final int STRIKE_NUMBER = 10;

    public static FrameResult create(int numberOfHitPin) {
        if (numberOfHitPin == STRIKE_NUMBER) {
            return new StrikeFrameResult();
        }
        return NormalFrameResult.firstThrow(numberOfHitPin);
    }
}
