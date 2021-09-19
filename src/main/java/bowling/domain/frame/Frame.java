package bowling.domain.frame;

import java.util.List;

public abstract class Frame {

    protected static int FIRST_TRIAL = 1;

    protected static int SECOND_TRIAL = 2;

    protected int trial;

    protected Frame(int trial) {
        this.trial = trial;
    }

    public boolean isNowFirstTry() {
        return trial == FIRST_TRIAL;
    }

    public boolean isNowSecondTry() {
        return trial == SECOND_TRIAL;
    }

    public abstract int next();

    public abstract List<Integer> getAllScores();

    protected abstract boolean isLast();

    protected abstract Frame tryNext(int score);
}
