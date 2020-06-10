package bowling.step2.domain;

public class FrameScore {
    private final Score score;
    private final boolean spared;

    private FrameScore (Score score, boolean spared) {
        this.score = score;
        this.spared = spared;
    }

    public static FrameScore of (Score score, boolean spared) {
        return new FrameScore(score, spared);
    }
}