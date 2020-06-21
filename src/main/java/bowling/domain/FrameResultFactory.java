package bowling.domain;

import bowling.domain.FrameResult.FinalFrameResult;
import bowling.domain.FrameResult.FrameResult;
import bowling.domain.FrameResult.NormalFrameResult;
import bowling.domain.FrameResult.StrikeFrameResult;

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
