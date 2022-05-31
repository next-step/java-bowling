package bowling.domain.state;

import bowling.domain.Pins;

public class FirstBowl implements State {
    private Pins firstPins;

    FirstBowl(int hitCount) {
        this.firstPins = new Pins(hitCount);
    }

    @Override
    public State bowl(int secondHitCount) {
        if (firstPins.isHitAllAfter(secondHitCount)) {
            return State.ofSpare(firstPins.hitCount());
        }

        return new Miss(firstPins.hitCount(), secondHitCount);
    }

    @Override
    public boolean isDone() {
        return false;
    }

}
