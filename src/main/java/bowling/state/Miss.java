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
    public Score calculateAdditionalScore(Score beforeScore) {
        Score afterScore = beforeScore.addScore(firstPins.getFalledPins());
        if (afterScore.canCalculate()) {
            return afterScore;
        }
        return afterScore.addScore(secondPins.getFalledPins());
    }

    @Override
    public String getDesc() {
        String first = String.valueOf(firstPins.getFalledPins());
        String second = String.valueOf(secondPins.getFalledPins());
        if ("0".equals(first)) {
            first = StateSymbol.GUTTER.symbol();
        }

        if ("0".equals(second)) {
            second = StateSymbol.GUTTER.symbol();
        }

        return first + "|" + second;
    }

    @Override
    public boolean canAddBonusPins() {
        return false;
    }
}
