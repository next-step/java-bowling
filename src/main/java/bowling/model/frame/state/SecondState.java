package bowling.model.frame.state;

import bowling.model.Pin;
import bowling.model.frame.State;

import static bowling.model.Pin.DOWN_ALL;
import static java.lang.Boolean.TRUE;

public abstract class SecondState extends FirstState {

    private final Pin secondBowl;

    SecondState(Pin firstBowl, Pin secondBowl) {
        super(firstBowl);
        this.secondBowl = secondBowl;
    }

    static State of(Pin firstBowl, Pin secondBowl) {
        Pin totalPin = firstBowl.sum(secondBowl);

        if (DOWN_ALL.equals(totalPin)) {
            return Spare.valueOf(firstBowl);
        }
        return Miss.valueOf(firstBowl, secondBowl);
    }

    Pin getSecondBowl() {
        return secondBowl;
    }

    @Override
    public State bowl(Pin secondBowl) {
        throw new CanNotBowlException();
    }

    @Override
    public Score calculate(Score prevScore) {
        Score calculatedScore = prevScore.calculate(getFirstBowl());
        if (!calculatedScore.isCompleted()) {
            calculatedScore = calculatedScore.calculate(getSecondBowl());
        }
        return calculatedScore;
    }

    @Override
    public boolean isFinished() {
        return TRUE;
    }
}