package bowling.domain.state.finish;

import bowling.domain.score.Pin;
import bowling.domain.score.Score;
import bowling.exception.state.MissStateCrerateException;

public class Miss extends Finish {

    private final Pin first;
    private final Pin second;

    public Miss(Pin first, Pin second) {
        checkMissCreatePins(first, second);

        this.first = first;
        this.second = second;
    }

    private static void checkMissCreatePins(Pin first, Pin second) {
        if (!first.isMiss(second)) {
            throw new MissStateCrerateException();
        }
    }

    @Override
    public Score createScore() {
        return Score.miss(first.sum(second));
    }

}
