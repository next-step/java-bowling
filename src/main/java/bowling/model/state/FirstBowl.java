package bowling.model.state;

import bowling.model.CannotCalculateException;
import bowling.model.Score;

public class FirstBowl extends Running{
    private final Pin firstPins;

    FirstBowl(int falledPins) {
        this.firstPins = new Pin(falledPins);
    }

    @Override
    public State bowl(int falledPins) {
        Pin secondPins = new Pin(falledPins);
        if (firstPins.isSpare(secondPins)) {
            return new Spare(firstPins, secondPins);
        }

        return new Miss(firstPins, secondPins);
    }

    @Override
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
