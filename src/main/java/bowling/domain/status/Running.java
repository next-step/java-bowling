package bowling.domain.status;

import bowling.domain.result.Score;

public abstract class Running implements Status {
    @Override
    public boolean canPlayMore() {
        return true;
    }

    @Override
    public Score getScore() {
        throw new CannotCalculateException();
    }
}
