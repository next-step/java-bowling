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
        if (first.equals("0")) {
            first = "-";
        }

        if (second.equals("0")) {
            second = "-";
        }

        return first + "|" + second;
    }
}
