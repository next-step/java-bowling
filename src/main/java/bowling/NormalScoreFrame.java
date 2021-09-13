package bowling;

public class NormalScoreFrame extends ScoreFrame {

    public NormalScoreFrame(Turn turn) {
        super(turn);
    }

    @Override
    public ScoreFrame addScore(int scoreValue) {
        frameMeta.add(new Score(scoreValue));

        if (frameMeta.isNormalEnd()) {
            next = createNextFrame();

            return next;
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
            return isCalculable(frameResult.getBonusScoreCount(), this.getNextFrame());
        }

        return false;
    }

    @Override
    public Score getScore(Score previousScore) {
        FrameResult frameResult = frameMeta.getFrameResult();
        Score currentFrameScore = previousScore.sum(frameMeta.getTotalSumScore());

        if (frameResult.isBonusResult()) {
            return getScore(frameResult.getBonusScoreCount(), currentFrameScore, getNextFrame());
        }

        return currentFrameScore;
    }

    private boolean isCalculable(int leftBonusScoreCount, ScoreFrame currentScoreFrame) {
        int remainBonusCount = leftBonusScoreCount - currentScoreFrame.getCount();

        if (isEnd(remainBonusCount)) {
            return true;
        }

        if (currentScoreFrame.hasNextFrame()) {
            return isCalculable(remainBonusCount, currentScoreFrame.getNextFrame());
        }

        return false;
    }

    private Score getScore(int leftBonusScoreCount, Score previousScore, ScoreFrame currentScoreFrame) {
        if (isEnd(leftBonusScoreCount)) {
            return previousScore;
        }

        return getScore(leftBonusScoreCount - currentScoreFrame.getCount(),
                previousScore.sum(currentScoreFrame.getSumScore(leftBonusScoreCount)), currentScoreFrame.getNextFrame());
    }

    private ScoreFrame createNextFrame() {
        Turn nextTurn = frameMeta.getNextTurn();

        if (frameMeta.isLastNormalTurn()) {
            return new FinalScoreFrame(nextTurn);
        }

        return new NormalScoreFrame(nextTurn);
    }

    private boolean isEnd(int depth) {
        return depth <= 0;
    }
}
