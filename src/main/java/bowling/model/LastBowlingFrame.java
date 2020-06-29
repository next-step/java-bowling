package bowling.model;

import static bowling.model.Pins.MIN_PIN_COUNT;

public class LastBowlingFrame implements BowlingFrame {

    private static final int MAX_LAST_FRAME_BOWLABLE_COUNT = 3;

    private final FrameScore frameScore;
    private final Pins pins;

    private LastBowlingFrame(final FrameScore frameScore, final Pins pins) {
        this.frameScore = frameScore;
        this.pins = pins;
    }

    public static LastBowlingFrame newInstance() {
        return new LastBowlingFrame(new FrameScore(), new Pins());
    }

    @Override
    public void bowl(final int scoreCount) {
        frameScore.add(pins.drop(scoreCount));

        if (!frameScore.isSameScoreCount(MAX_LAST_FRAME_BOWLABLE_COUNT) && pins.isRemain(MIN_PIN_COUNT)) {
            pins.reset();
        }
    }

    @Override
    public boolean isOver() {
        if (frameScore.isSameScoreCount(MAX_LAST_FRAME_BOWLABLE_COUNT)) {
            return true;
        }

        if (frameScore.isSameScoreCount(MAX_LAST_FRAME_BOWLABLE_COUNT - 1)) {
            return !canBowlOneMore();
        }

        return false;
    }

    private boolean canBowlOneMore() {
        return frameScore.canBowlOneMore();
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
