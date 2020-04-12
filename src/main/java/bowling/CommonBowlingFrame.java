package bowling;

public class CommonBowlingFrame implements BowlingFrame {
    private static final int MAX_COMMON_FRAME_BOWL_COUNT = 2;

    private final FrameScore frameScore;
    private final Pins pins;

    private CommonBowlingFrame(final FrameScore frameScore, final Pins pins) {
        this.frameScore = frameScore;
        this.pins = pins;
    }

    public static CommonBowlingFrame newInstance() {
        return new CommonBowlingFrame(new FrameScore(), new Pins());
    }

    @Override
    public void bowl(final int scoreCount) {
        frameScore.add(pins.drop(scoreCount));
    }

    @Override
    public boolean isOver() {
        return pins.isRemain(0) || frameScore.isCount(MAX_COMMON_FRAME_BOWL_COUNT);
    }

    @Override
    public int sum() {
        return frameScore.sum();
    }
}
