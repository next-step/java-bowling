package bowling;

public class BowlingFrames {
    private final BowlingFrame firstFrame;
    private BowlingFrame activeFrame;

    private BowlingFrames(final BowlingFrame firstFrame, final BowlingFrame activeFrame) {
        this.firstFrame = firstFrame;
        this.activeFrame = activeFrame;
    }

    public static BowlingFrames newInstance() {
        BowlingFrame firstFrame = CommonBowlingFrame.newInstance(1);
        BowlingFrame activeFrame = firstFrame;

        return new BowlingFrames(firstFrame, activeFrame);
    }

    public void bowl(final int countOfPin) {
        activeFrame.bowl(countOfPin);
    }

}
