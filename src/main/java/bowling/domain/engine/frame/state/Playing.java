package bowling.domain.engine.frame.state;

import bowling.domain.engine.roll.RollResult;

public class Playing implements State {

    private final RollResult firstRoll;

    public Playing(RollResult firstRoll) {
        this.firstRoll = firstRoll;
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public State transit(RollResult secondRoll) {
        if (firstRoll.isClearWith(secondRoll)) {
            return new Spare(firstRoll, secondRoll);
        }
        return new Miss(firstRoll, secondRoll);
    }

    @Override
    public String export() {
        return firstRoll.export();
    }
}
