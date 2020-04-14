package bowling;

import static bowling.Pins.MIN_PIN_COUNT;

public class CommonBowlingFrame implements BowlingFrame {
    private static final int MAX_COMMON_FRAME_BOWLABLE_COUNT = 2;

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
        return pins.isRemain(MIN_PIN_COUNT) || frameScore.isCount(MAX_COMMON_FRAME_BOWLABLE_COUNT);
    }

    @Override
    public int sum() {
        return frameScore.sum();
    }

    @Override
    public FrameScore getFrameScore() {
        return frameScore;
    }
}
