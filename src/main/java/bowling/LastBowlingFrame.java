package bowling;

public class LastBowlingFrame implements BowlingFrame {
    private static final int MAX_LAST_FRAME_BOWL_COUNT = 3;

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
    public void bowl(int scoreCount) {
        validateBowl();
        frameScore.add(pins.drop(scoreCount));

        if (!frameScore.isCount(MAX_LAST_FRAME_BOWL_COUNT) && pins.isRemain(0)) {
            pins.reset();
        }
    }

    private void validateBowl() {
        if (frameScore.isCount(MAX_LAST_FRAME_BOWL_COUNT - 1) && !hasStrikeOrSpareInTwoScores()) {
            throw new RuntimeException("Can not bowl the third.");
        }
    }

    @Override
    public boolean isOver() {
        if(frameScore.isCount(MAX_LAST_FRAME_BOWL_COUNT)) {
            return true;
        }

        if(frameScore.isCount(MAX_LAST_FRAME_BOWL_COUNT - 1)) {
            return !hasStrikeOrSpareInTwoScores();
        }

        return false;
    }

    @Override
    public int sum() {
        return frameScore.sum();
    }

    private boolean hasStrikeOrSpareInTwoScores() {
        return frameScore.isStrike() || frameScore.isSpare();
    }
}
