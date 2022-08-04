package bowling.domain;

import bowling.domain.frame.Frame;
import bowling.domain.score.ScoreStrategyFactory;

public class ScoreCalculator {

    public static void handlePending(Frame frame) {
        frame.computeScore(ScoreStrategyFactory.getScoreStrategy(frame));
    }

    public static void calculate(Frame frame) {
        frame.computeScore(ScoreStrategyFactory.getScoreStrategy(frame));
    }
}
