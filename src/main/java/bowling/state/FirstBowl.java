package bowling.state;

import bowling.Pin;
import bowling.Score;

public class FirstBowl extends Running {

    private final Pin firstPins;

    public FirstBowl(Pin firstPins) {
        this.firstPins = firstPins;
    }

    @Override
    public State bowl(Pin falledPins) {
        if (firstPins.isSpare(falledPins.getFalledPins())) {
            return new Spare(firstPins, falledPins);
        }
        return new Miss(firstPins, falledPins);
    }

    @Override
    public Score calculateAdditionalScore(Score beforeScore) {
        return beforeScore.addScore(firstPins.getFalledPins());
    }

    @Override
    public String getDesc() {
        if (firstPins.getFalledPins() == 0) {
            return "-";
        }
        return String.valueOf(firstPins.getFalledPins());
    }
}
