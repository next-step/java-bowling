package bowling.domain.score;

public class FrameScore {

    private final int frameScore;
    private final int leftPitchCounts;

    private FrameScore(int frameScore, int leftPitchCounts) {
        this.frameScore = frameScore;
        this.leftPitchCounts = leftPitchCounts;
    }

    public static FrameScore ofMiss(int frameScore) {
        return new FrameScore(frameScore, 0);
    }

    public static FrameScore ofStrike() {
        return new FrameScore(10, 2);
    }

    public static FrameScore ofSpare() {
        return new FrameScore(10, 1);
    }

    public int getFrameScore() {
        return frameScore;
    }

    public boolean isAbleToCalculate() {
        return leftPitchCounts == 0;
    }

    public FrameScore next(int hitCounts) {
        return new FrameScore(this.frameScore + hitCounts, leftPitchCounts - 1);
    }
}
