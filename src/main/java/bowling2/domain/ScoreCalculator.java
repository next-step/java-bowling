package bowling2.domain;

import bowling2.domain.frame.Frame;
import bowling2.domain.score.ScoreStrategy;
import bowling2.domain.score.ScoreStrategyFactory;

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
        ScoreStrategy scoreStrategy = ScoreStrategyFactory.getScoreStrategy(frame);
//        frame.computeScore(scoreStrategy);
    }

    public static void pendingOrCalculate(Frame currentFrame) {
        // TODO(jack.comeback) : logic
        // if currentFrame이 스트라이크나 스페어면 큐에 추가한다.
        // 아니라면
        // 점수 전략 + 점수 계산 메서드화
            // 점수 전략을 꺼낸댜.
            // Frame에게 점수 전략을 넘기며 점수 계산 요청한다.
    }
}
