package bowling;

public class NormalScoreFrame extends ScoreFrame {

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
            return calculable(frameResult.getBonusDepth(), this.getNextFrame());
        }

        return false;
    }

    @Override
    public Score getScore(Score previousScore) {
        FrameResult frameResult = frameMeta.getFrameResult();
        Score currentFrameScore = previousScore.sum(frameMeta.getTotalSumScore());

        if (frameResult.isBonusResult()) {
            return addScore(frameResult.getBonusDepth(), currentFrameScore, getNextFrame());
        }

        return currentFrameScore;
    }

    private Score addScore(int depth, Score previousScore, ScoreFrame currentScoreFrame) {
        if (isEnd(depth)) {
            return previousScore;
        }

        return addScore(depth - currentScoreFrame.getCount(),
                previousScore.sum(currentScoreFrame.getSumScore(depth)), currentScoreFrame.getNextFrame());
    }

    private boolean calculable(int depth, ScoreFrame currentScoreFrame) {
        int remainDepth = depth - currentScoreFrame.getCount();

        if (isEnd(remainDepth)) {
            return true;
        }

        if (currentScoreFrame.hasNextFrame()) {
            return calculable(remainDepth, currentScoreFrame.getNextFrame());
        }

        return false;
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
