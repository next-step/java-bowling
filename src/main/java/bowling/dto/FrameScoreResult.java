package bowling.dto;

import bowling.FrameScore;

import static bowling.dto.FrameScoreConverter.joinFrameScoreString;

public class FrameScoreResult {

    private final String scoreResult;

    private FrameScoreResult(final String scoreResult) {
        this.scoreResult = scoreResult;
    }

    public static FrameScoreResult newInstance(final FrameScore frameScore) {
        return new FrameScoreResult(joinFrameScoreString(frameScore));
    }

    public String getScoreResult() {
        return scoreResult;
    }
}
