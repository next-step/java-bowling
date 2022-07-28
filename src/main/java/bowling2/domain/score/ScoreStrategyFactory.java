package bowling2.domain.score;

import bowling2.domain.frame.Frame;
import bowling2.exception.BowlingException;

public class ScoreStrategyFactory {
    public ScoreStrategy getScoreStrategy(Frame frame) {
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
