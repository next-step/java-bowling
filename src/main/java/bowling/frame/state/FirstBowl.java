package bowling.frame.state;

import bowling.frame.CannotCalculateException;
import bowling.frame.Score;

class FirstBowl extends Running {
    private final Pins firstPins;

    FirstBowl(Pins falledPins) {
        this.firstPins = falledPins;
    }

    @Override
    public State bowl(int falledPins) {
        Pins pins = Pins.bowl(falledPins);
        if (firstPins.isSpare(pins)) {
            return new Spare(firstPins, pins);
        }
        return new Miss(firstPins, pins);
    }

    public Score calculateAdditionalScore(Score score) {
        score = firstPins.sumScore(score);
        if (score.canCalculateScore()) {
            return score;
        }
        throw new CannotCalculateException();
    }

    @Override
    public String getDesc() {
        return firstPins.getDesc();
    }
}
