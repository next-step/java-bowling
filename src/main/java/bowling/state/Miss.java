package bowling.state;

import bowling.Pin;
import bowling.Score;

public class Miss extends Finished {

    private final Pin firstPins;
    private final Pin secondPins;

    public Miss(Pin firstPins, Pin secondPins) {
        this.firstPins = firstPins;
        this.secondPins = secondPins;
    }

    @Override
    public Score score() {
        return Score.ofMiss(firstPins.getFalledPins() + secondPins.getFalledPins());
    }

    @Override
    public Score calculatorAdditionalScore(Score beforeScore) {
        Score afterScore = beforeScore.addScore(firstPins.getFalledPins());
        if (afterScore.canCalculate()) {
            return afterScore;
        }
        return afterScore.addScore(secondPins.getFalledPins());
    }

    @Override
    public String getDesc() {
        return firstPins.getFalledPins() + "|" + secondPins.getFalledPins();
    }
}
