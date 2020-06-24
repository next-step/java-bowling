package bowling.fixture;

import bowling.domain.frame.Frames;
import bowling.domain.pin.PinCount;

public class FramesFixture {

    public FramesFixture() {
    }

    public static Frames getSpareFrames() {
        Frames frames = Frames.newInstance();
        frames.bowl(PinCount.of(9));
        frames.bowl(PinCount.of(1));

        return frames;
    }

    public static Frames getTwoStrikeFrames() {
        Frames frames = Frames.newInstance();
        frames.bowl(PinCount.of(PinCount.MAX_COUNT));
        frames.bowl(PinCount.of(PinCount.MAX_COUNT));

        return frames;
    }

    public static Frames getStrikeHitFrames() {
        Frames frames = Frames.newInstance();
        frames.bowl(PinCount.of(PinCount.MAX_COUNT));
        frames.bowl(PinCount.of(9));

        return frames;
    }
}
