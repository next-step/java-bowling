package bowling.fixture;

import bowling.domain.frame.FinalFrame;
import bowling.domain.pin.PinCount;

public class FinalFrameFixture {

    public FinalFrameFixture() {
    }

    public static FinalFrame getOneStrikeFrame() {
        FinalFrame oneStrikeFrame = FinalFrame.newInstance();
        oneStrikeFrame.bowl(PinCount.of(PinCount.MAX_COUNT));

        return oneStrikeFrame;
    }

    public static FinalFrame getStrikeGutterFrame() {
        FinalFrame strikeGutterFrame = FinalFrame.newInstance();
        strikeGutterFrame.bowl(PinCount.of(PinCount.MAX_COUNT));
        strikeGutterFrame.bowl(PinCount.of(PinCount.MIN_COUNT));

        return strikeGutterFrame;
    }

    public static FinalFrame getHitMissFrame() {
        FinalFrame hitMissFrame = FinalFrame.newInstance();
        hitMissFrame.bowl(PinCount.of(5));
        hitMissFrame.bowl(PinCount.of(1));

        return hitMissFrame;
    }

    public static FinalFrame getThreeStrikeFrame() {
        FinalFrame threeStrikeFrame = FinalFrame.newInstance();
        threeStrikeFrame.bowl(PinCount.of(PinCount.MAX_COUNT));
        threeStrikeFrame.bowl(PinCount.of(PinCount.MAX_COUNT));
        threeStrikeFrame.bowl(PinCount.of(PinCount.MAX_COUNT));

        return threeStrikeFrame;
    }

    public static FinalFrame getHitSpareStrikeFrame() {
        FinalFrame hitSpareStrikeFrame = FinalFrame.newInstance();
        hitSpareStrikeFrame.bowl(PinCount.of(9));
        hitSpareStrikeFrame.bowl(PinCount.of(1));
        hitSpareStrikeFrame.bowl(PinCount.of(PinCount.MAX_COUNT));

        return hitSpareStrikeFrame;
    }

    public static FinalFrame getHitSpareHitFrame() {
        FinalFrame hitSpareHitFrame = FinalFrame.newInstance();
        hitSpareHitFrame.bowl(PinCount.of(9));
        hitSpareHitFrame.bowl(PinCount.of(1));
        hitSpareHitFrame.bowl(PinCount.of(2));

        return hitSpareHitFrame;
    }

    public static FinalFrame getStrikeHitMissFrame() {
        FinalFrame strikeHitMissFrame = FinalFrame.newInstance();
        strikeHitMissFrame.bowl(PinCount.of(PinCount.MAX_COUNT));
        strikeHitMissFrame.bowl(PinCount.of(5));
        strikeHitMissFrame.bowl(PinCount.of(1));

        return strikeHitMissFrame;
    }
}
