package bowling.domian.state.finished;

import bowling.domian.frame.Score;
import bowling.domian.state.Pins;

public class Miss extends Finished {
    private Pins firstPins;
    private Pins second;

    public Miss(Pins firstPins, Pins second) {
        this.firstPins = firstPins;
        this.second = second;
    }

    @Override
    public Score getScore() {
        return Score.miss(firstPins.totalPinsCount(second));
    }

    @Override
    public Score calculateAdditional(Score lastScore) {
        lastScore = firstPins.addScore(lastScore);

        if (!lastScore.isCalculateDone()) {
            lastScore = second.addScore(lastScore);
        }

        return lastScore;
    }

    @Override
    public String getDesc() {
        return firstPins.getDesc(second);
    }
}
