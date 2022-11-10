package bowling.domain.frame.state;

import java.util.List;

import bowling.domain.frame.Score;

public class FirstBowl implements State {
    private final Pins firstPins;

    public FirstBowl(Pins firstPins) {
        this.firstPins = firstPins;
    }

    @Override
    public State bowl(int pins) {
        Pins secondPins = new Pins(pins);
        if (firstPins.isSpare(secondPins)) {
            return new Spare(firstPins, secondPins);
        }

        return new Miss(firstPins, secondPins);
    }

    @Override
    public Score createScore() {
        return new Score(List.of(firstPins.getPins()));
    }

    @Override
    public boolean isFinish() {
        return false;
    }

    @Override
    public boolean canBonusBowl() {
        return true;
    }
}
