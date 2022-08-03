package bowling.domain;

import bowling.domain.frame.Frame;
import bowling.domain.score.ScoreStrategyFactory;

import java.util.Optional;

public class ScoreCalculator {

    private ScorePendingQueue scorePendingQueue;

    public static ScoreCalculator init() {
        return new ScoreCalculator(new ScorePendingQueue());
    }

    public ScoreCalculator() {
    }

    public ScoreCalculator(ScorePendingQueue scorePendingQueue) {
        this.scorePendingQueue = scorePendingQueue;
    }

    public Optional<PendingFrame> findPreparedPending() {
        scorePendingQueue.minusPopCount();
        return scorePendingQueue.getPreparedPending();
    }

    public void handlePending(Frame frame) {
        frame.computeScore(ScoreStrategyFactory.getScoreStrategy(frame));
    }

    public void pendingOrCalculate(Frame currentFrame) {
        if (currentFrame.isCommonScoreType()) {
            currentFrame.computeScore(ScoreStrategyFactory.getScoreStrategy(currentFrame));
        }
        if (currentFrame.isStrikeScoreType()) {
            scorePendingQueue.add(PendingFrame.strike(currentFrame.getIndex()));
        }
        if (currentFrame.isSpareScoreType()) {
            scorePendingQueue.add(PendingFrame.spare(currentFrame.getIndex()));
        }
    }
}
