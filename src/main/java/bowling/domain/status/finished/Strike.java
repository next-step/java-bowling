package bowling.domain.status.finished;

import bowling.domain.result.Score;
import bowling.domain.status.Finished;

public class Strike extends Finished {
    @Override
    public String printResult() {
        return "X";
    }

    @Override
    public boolean isClearAllPins() {
        return true;
    }

    @Override
    public Score getScore() {
        return new Score(10, 2);
    }
}
