package bowling.fixture;

import bowling.domain.frame.NormalFrame;
import bowling.domain.pin.PinCount;

public class NormalFrameFixture {

    public NormalFrameFixture() {
    }

    public static NormalFrame getReadyFrame() {
        return NormalFrame.ofFirst();
    }

    public static NormalFrame getStrikeFrame() {
        NormalFrame strikeFrame = NormalFrame.ofFirst();
        strikeFrame.bowl(PinCount.of(PinCount.MAX_COUNT));

        return strikeFrame;
    }

    public static NormalFrame getSpareFrame() {
        NormalFrame spareFrame = NormalFrame.ofFirst();
        spareFrame.bowl(PinCount.of(1));
        spareFrame.bowl(PinCount.of(9));

        return spareFrame;
    }

    public static NormalFrame getMissFrame() {
        NormalFrame missFrame = NormalFrame.ofFirst();
        missFrame.bowl(PinCount.of(2));
        missFrame.bowl(PinCount.of(3));

        return missFrame;
    }

    public static NormalFrame getOneHitFrame() {
        NormalFrame oneHitFrame = NormalFrame.ofFirst();
        oneHitFrame.bowl(PinCount.of(1));

        return oneHitFrame;
    }

    public static NormalFrame getGutterFrame() {
        NormalFrame gutterFrame = NormalFrame.ofFirst();
        gutterFrame.bowl(PinCount.of(PinCount.MIN_COUNT));

        return gutterFrame;
    }
}
