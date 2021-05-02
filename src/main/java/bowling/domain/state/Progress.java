package bowling.domain.state;

import bowling.domain.exception.CannotCalculateException;
import bowling.domain.score.Score;

public interface Progress extends State {

    default boolean isDone() {
        return false;
    }

    default Score score() {
        throw new CannotCalculateException("점수를 계산할 수 없는 상태입니다.");
    }
}
