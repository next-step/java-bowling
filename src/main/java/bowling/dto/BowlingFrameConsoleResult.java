package bowling.dto;

import bowling.Score;
import bowling.frame.BowlingFrame;
import bowling.framestate.State;
import bowling.view.FrameScoreConsoleResult;

public class BowlingFrameConsoleResult {

    private final State frameState;
    private final String totalScore;

    private BowlingFrameConsoleResult(final State frameState, final String totalScore) {
        this.frameState = frameState;
        this.totalScore = totalScore;
    }

    public static BowlingFrameConsoleResult newInstance(final BowlingFrame bowlingFrame, final Score totalScore) {
        return new BowlingFrameConsoleResult(bowlingFrame.getState(), makeTotalScore(bowlingFrame, totalScore));
    }

    private static String makeTotalScore(final BowlingFrame bowlingFrame, final Score totalScore) {
        if (bowlingFrame.canCalculateScore()) {
            return String.valueOf(totalScore.getScore());
        }

        return "";
    }

    public State getFrameState() {
        return frameState;
    }

    public String getTotalScore() {
        return totalScore;
    }
}
