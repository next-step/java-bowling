package bowling.state.ended;

import bowling.Pins;
import bowling.frame.Score;

public class Miss extends Ended {

    private final Pins beforePins;
    private final Pins afterPins;

    public Miss(Pins beforePins, Pins afterPins) {
        this.beforePins = beforePins;
        this.afterPins = afterPins;
    }

    @Override
    public String symbol() {
        return beforePins.symbol() + "|" + afterPins.symbol();
    }

    @Override
    public boolean isMiss() {
        return true;
    }

    @Override
    public Score getScore() {
        return Score.ofMiss(beforePins.totalPinCount(afterPins));
    }

    @Override
    public Score calculateAdditionalScore(Score beforeScore) {
        beforeScore = this.beforePins.sumScore(beforeScore);
        if (beforeScore.isCalculatorScore()) {
            return beforeScore;
        }
        return afterPins.sumScore(beforeScore);
    }
}
