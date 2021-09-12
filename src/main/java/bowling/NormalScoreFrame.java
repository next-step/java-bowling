package bowling;

public class NormalScoreFrame extends ScoreFrame {
    private static final int MISS_TRIAL_DEPTH = 0;
    private static final int SPARE_TRIAL_DEPTH = 1;
    private static final int STRIKE_TRIAL_DEPTH = 2;

    public NormalScoreFrame(Turn turn) {
        super(turn);
    }

    @Override
    public ScoreFrame addScore(int scoreValue) {
        frameMeta.add(new Score(scoreValue));

        if (frameMeta.isNormalEnd()) {
            return next = createNextFrame();
        }

        return this;
    }

    @Override
    public boolean isCalculable() {
        FrameResult frameResult = frameMeta.getFrameResult();

        if (frameResult.equals(FrameResult.MISS)) {
            return true;
        }

        if (frameResult.isBonusResult() && this.hasNextFrame()) {
            return calculable(getDepth(frameResult), this.getNextFrame());
        }

        return false;
    }

    @Override
    public Score getScore(Score previousScore) {
        FrameResult frameResult = frameMeta.getFrameResult();
        Score currentFrameScore = previousScore.sum(frameMeta.getTotalSumScore());

        if (frameResult.isBonusResult()) {
            return addScore(getDepth(frameResult), currentFrameScore, getNextFrame());
        }

        return currentFrameScore;
    }

    public Score addScore(int depth, Score previousScore, ScoreFrame currentScoreFrame) {
        if (depth <= 0) {
            return previousScore;
        }

        return addScore(depth - currentScoreFrame.getCount(),
                previousScore.sum(currentScoreFrame.getSumScore(depth)), currentScoreFrame.getNextFrame());
    }

    private boolean calculable(int depth, ScoreFrame currentScoreFrame) {
        int remainDepth = depth - currentScoreFrame.getCount();

        if (remainDepth <= 0) {
            return true;
        }

        if (currentScoreFrame.hasNextFrame()) {
            return calculable(remainDepth, currentScoreFrame.getNextFrame());
        }

        return false;
    }

    private int getDepth(FrameResult frameResult) {
        if (frameResult == FrameResult.SPARE) {
            return SPARE_TRIAL_DEPTH;
        }

        if (frameResult == FrameResult.STRIKE) {
            return STRIKE_TRIAL_DEPTH;
        }

        return MISS_TRIAL_DEPTH;
    }

    private ScoreFrame createNextFrame() {
        Turn nextTurn = frameMeta.getNextTurn();

        if (frameMeta.isLastNormalTurn()) {
            return new FinalScoreFrame(nextTurn);
        }

        return new NormalScoreFrame(nextTurn);
    }
}
