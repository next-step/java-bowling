package bowling.domain.score;

public class FrameScore {

    private final int frameScoreTotal;

    private FrameScore(int frameScoreTotal) {
        this.frameScoreTotal = frameScoreTotal;
    }

    public static FrameScore of(int frameScoreTotal) {
        return new FrameScore(frameScoreTotal);
    }

    public int getFrameScoreTotal() {
        return frameScoreTotal;
    }
}
