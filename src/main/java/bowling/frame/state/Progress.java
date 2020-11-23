package bowling.frame.state;

import bowling.global.exception.CannotCalculateException;
import bowling.score.Score;

public abstract class Progress extends State {

    @Override
    public boolean isFinish() {
        return false;
    }

    @Override
    public Score getScore() {
        throw new CannotCalculateException();
    }

}
