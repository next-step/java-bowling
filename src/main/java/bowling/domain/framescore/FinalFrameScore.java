package bowling.domain.framescore;

public class FinalFrameScore extends Normal {
    private final FrameScore baseFrameScore;

    public FinalFrameScore(final FrameScore baseFrameScore, boolean showScoreValue) {
        super(baseFrameScore.turnScores(), showScoreValue);
        this.baseFrameScore = baseFrameScore;
    }

    public boolean isSpare() {
        return baseFrameScore instanceof Spare;
    }
}
