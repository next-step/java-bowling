package bowling.domian.state;

import bowling.domian.frame.Score;
import bowling.domian.state.exception.BowlFinishedException;

public class Miss extends Finished {

    private Pins first;
    private Pins second;

    public Miss(Pins first, Pins second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public Score getScore() {
        return Score.miss(first.totalPinsCount(second));
    }

    @Override
    public Score calculateAdditional(Score lastScore) {
        lastScore = first.addScore(lastScore);

        if (!lastScore.isCalculateDone()) {
            lastScore = second.addScore(lastScore);
        }

        return lastScore;
    }
}
