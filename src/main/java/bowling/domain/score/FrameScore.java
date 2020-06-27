package bowling.domain.score;

public class FrameScore {
    private static final int ZERO = 0;
    private static final int ONE = 1;
    private static final int MAXIMUM_PITCH_SCORE = 10;
    private static final int BONUS_PITCH_COUNTS_FOR_SPARE = 1;
    private static final int BONUS_PITCH_COUNTS_FOR_STRIKE = 2;

    private final int frameScore;
    private final int leftPitchCounts;

    private FrameScore(int frameScore, int leftPitchCounts) {
        this.frameScore = frameScore;
        this.leftPitchCounts = leftPitchCounts;
    }

    public static FrameScore ofMiss(int frameScore) {
        return new FrameScore(frameScore, ZERO);
    }

    public static FrameScore ofStrike() {
        return new FrameScore(MAXIMUM_PITCH_SCORE, BONUS_PITCH_COUNTS_FOR_STRIKE);
    }

    public static FrameScore ofSpare() {
        return new FrameScore(MAXIMUM_PITCH_SCORE, BONUS_PITCH_COUNTS_FOR_SPARE);
    }

    public FrameScore next(int pitchScore) {
        return new FrameScore(frameScore + pitchScore, leftPitchCounts - ONE);
    }

    public boolean isAbleToCalculate() {
        return leftPitchCounts == ZERO;
    }

    public int getFrameScore() {
        return frameScore;
    }
}
