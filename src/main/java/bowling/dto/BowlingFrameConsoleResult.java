package bowling.dto;

import bowling.Score;
import bowling.frame.BowlingFrame;

public class BowlingFrameConsoleResult {

    private final FrameState frameState;
    private final String totalScore;

    private BowlingFrameConsoleResult(final FrameState frameState, final String totalScore) {
        this.frameState = frameState;
        this.totalScore = totalScore;
    }

    public static BowlingFrameConsoleResult newInstance(final BowlingFrame bowlingFrame, final Score totalScore) {
        return new BowlingFrameConsoleResult(FrameState.newInstance(bowlingFrame), makeTotalScore(bowlingFrame, totalScore));
    }

    private static String makeTotalScore(final BowlingFrame bowlingFrame, final Score totalScore) {
        if (bowlingFrame.canCalculateScore()) {
            return String.valueOf(totalScore.getScore());
        }

        return "";
    }

    public FrameState getFrameState() {
        return frameState;
    }

    public String getTotalScore() {
        return totalScore;
    }
}
