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

    public static Frames getTwoMissFrames() {
        Frames frames = Frames.newInstance();
        frames.bowl(PinCount.of(2));
        frames.bowl(PinCount.of(3));
        frames.bowl(PinCount.of(4));
        frames.bowl(PinCount.of(4));

        return frames;
    }

    public static Frames getSpareMissFrames() {
        Frames frames = Frames.newInstance();
        frames.bowl(PinCount.of(9));
        frames.bowl(PinCount.of(1));
        frames.bowl(PinCount.of(4));
        frames.bowl(PinCount.of(4));

        return frames;
    }

    public static Frames getStrikeMissFrames() {
        Frames frames = Frames.newInstance();
        frames.bowl(PinCount.of(10));
        frames.bowl(PinCount.of(4));
        frames.bowl(PinCount.of(4));

        return frames;
    }
}
