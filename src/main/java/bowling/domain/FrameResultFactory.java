package bowling.domain;

import bowling.domain.frameResult.FinalFrameResult;
import bowling.domain.frameResult.FrameResult;
import bowling.domain.frameResult.NormalFrameResult;
import bowling.domain.frameResult.StrikeFrameResult;

public class FrameResultFactory {
    private static final int STRIKE_NUMBER = 10;

    public static FrameResult create(int numberOfHitPin) {
        if (numberOfHitPin == STRIKE_NUMBER) {
            return new StrikeFrameResult();
        }
        return NormalFrameResult.firstBowl(numberOfHitPin);
    }

    public static FrameResult createFinal(int numberOfHitPin) {
        return FinalFrameResult.firstBowl(numberOfHitPin);
    }
}
