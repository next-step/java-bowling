package bowling;

import java.util.function.Function;

public enum FrameStatus {

    STRIKE(frame -> {
        Frame nextFrame = frame.getNextFrame();
        if (nextFrame.isLastFrame()) {
            return nextFrame.isSpare() || nextFrame.isDone();
        }
        if (nextFrame.isStrike()) {
            Frame nextNextFrame = nextFrame.getNextFrame();
            return nextNextFrame.isFirstDone();
        }
        return nextFrame.isDone();
    }, frame -> giveStrikeBonusScore(frame.getNextFrame())),
    SPARE(frame -> {
        Frame nextFrame = frame.getNextFrame();
        return nextFrame.isFirstDone();
    }, frame -> giveSpareBonusScore(frame.getNextFrame())),
    MISS(Frame::isDone, FrameStatus::giveDefaultBonusScore);

    private static final int DEFAULT_BONUS = 0;
    private final Function<Frame, Boolean> canCalculateScore;
    private final Function<Frame, Integer> getBonusScore;

    FrameStatus(Function<Frame, Boolean> canCalculateScore, Function<Frame, Integer> getBonusScore) {
        this.canCalculateScore = canCalculateScore;
        this.getBonusScore = getBonusScore;
    }

    public static FrameStatus of(Frame frame) {
        if (frame.isStrike()) {
            return STRIKE;
        }
        if (frame.isSpare()) {
            return SPARE;
        }
        return MISS;
    }

    public boolean canCalculateScore(Frame frame) {
        return canCalculateScore.apply(frame);
    }

    private static int giveStrikeBonusScore(Frame frame) {
        Pitchings pitchings = frame.getPitchings();
        if (frame.isLastFrame() && frame.isStrike()) {
            return pitchings.giveStrikeBonusScore() + pitchings.giveBonusScore();
        }
        if (frame.isStrike()) {
            Frame nextFrame = frame.getNextFrame();
            return pitchings.giveStrikeBonusScore() + giveSpareBonusScore(nextFrame);
        }
        return pitchings.giveStrikeBonusScore();
    }

    private static int giveSpareBonusScore(Frame frame) {
        Pitchings pitchings = frame.getPitchings();
        return pitchings.giveSpareBonusScore();
    }

    private static int giveDefaultBonusScore(Frame frame) {
        return DEFAULT_BONUS;
    }

    public int getBonusScore(Frame frame) {
        return getBonusScore.apply(frame);
    }
}
