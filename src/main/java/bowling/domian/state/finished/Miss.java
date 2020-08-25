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
        Score additionalScore = firstPins.addScore(lastScore);

        if (!additionalScore.isCalculateDone()) {
            additionalScore = second.addScore(additionalScore);
        }

        return additionalScore;
    }

    @Override
    public String getDesc() {
        return firstPins.getDesc(second);
    }
}
