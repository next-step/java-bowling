package bowling.domian.state;

import bowling.domian.frame.Score;

public class FirstBowl extends Running {

    private final Pins firstPins;

    public FirstBowl(Pins pins) {
        this.firstPins = pins;
    }

    @Override
    public State bowl(int falledPinsCount) {
        Pins secondPins = firstPins.secondBowl(falledPinsCount);

        if (secondPins.isSpare(firstPins)) {
            return new Spare(firstPins, secondPins);
        }

        return new Miss(firstPins, secondPins);
    }

    @Override
    public Score calculateAdditional(Score lastScore) {
        lastScore = firstPins.addScore(lastScore);

        return lastScore;
    }
}
