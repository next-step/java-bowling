package bowling.domian.state.running;

import bowling.domian.frame.Score;
import bowling.domian.state.Pins;
import bowling.domian.state.State;
import bowling.domian.state.finished.Miss;
import bowling.domian.state.finished.Spare;

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

    @Override
    public String getDesc() {
        return String.valueOf(firstPins.getPinsCount());
    }
}
