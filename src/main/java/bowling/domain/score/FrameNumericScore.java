package bowling.domain.score;

public class FrameNumericScore {

    private final int frameScoreTotal;

    private FrameNumericScore(int frameScoreTotal) {
        this.frameScoreTotal = frameScoreTotal;
    }

    public static FrameNumericScore of(int frameScoreTotal) {
        return new FrameNumericScore(frameScoreTotal);
    }

    public int getFrameScoreTotal() {
        return frameScoreTotal;
    }
}
