package bowling.domain.score;

import bowling.domain.frame.Frame;
import bowling.exception.BowlingException;

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
                throw new BowlingException("해당하는 scoreStrategy가 없습니다."); // TODO(jack.comeback) : 에러처리 디테일
        }
    }
}
