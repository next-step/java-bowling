package bowling.domain.state;

import bowling.domain.score.Score;
import bowling.exception.CannotCalculateException;

public interface Playing extends State {

    @Override
    default boolean isFinish() {
        return false;
    }

    @Override
    default Score getScore() {
        throw new CannotCalculateException();
    }
}
