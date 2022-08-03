package bowling.domain.score;

import bowling.domain.frame.Frame;
import bowling.exception.BowlingException;

import static bowling.exception.BowlingExceptionCode.NO_SUCH_SCORE_STRATEGY;

public class ScoreStrategyFactory {
    public static ScoreStrategy getScoreStrategy(Frame frame) {
        switch (frame.scoreType()) {
            case COMMON:
                return new CommonScoreStrategy();
            case SPARE:
                return new SpareScoreStrategy();
            case STRIKE:
                return new StrikeScoreStrategy();
            default:
                throw new BowlingException(NO_SUCH_SCORE_STRATEGY);
        }
    }
}
