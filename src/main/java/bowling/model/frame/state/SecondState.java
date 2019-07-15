package bowling.model.frame.state;

import bowling.model.Pins;
import bowling.model.frame.State;

import static bowling.model.Pins.DOWN_ALL;
import static java.lang.Boolean.TRUE;

public abstract class SecondState extends FirstState {

    private final Pins secondBowl;

    SecondState(Pins firstBowl, Pins secondBowl) {
        super(firstBowl);
        this.secondBowl = secondBowl;
    }

    static State of(Pins firstBowl, Pins secondBowl) {
        Pins totalPins = firstBowl.sum(secondBowl);

        if (DOWN_ALL.equals(totalPins)) {
            return Spare.valueOf(firstBowl);
        }
        return Miss.valueOf(firstBowl, secondBowl);
    }

    Pins getSecondBowl() {
        return secondBowl;
    }

    @Override
    public State bowl(Pins secondBowl) {
        throw new CanNotBowlException();
    }

    @Override
    public Score calculate(Score prevScore) {
        Score calculatedScore = prevScore.calculate(getFirstBowl());
        if (!prevScore.isCompleted()) {
            calculatedScore = calculatedScore.calculate(getSecondBowl());
        }
        return calculatedScore;
    }

    @Override
    public boolean isFinished() {
        return TRUE;
    }
}