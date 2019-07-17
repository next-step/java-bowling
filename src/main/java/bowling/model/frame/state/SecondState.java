package bowling.model.frame.state;

import bowling.model.DoublePins;
import bowling.model.DownPin;
import bowling.model.frame.State;

import static java.lang.Boolean.TRUE;

public abstract class SecondState implements State {

    private final DoublePins doublePins;

    SecondState(DoublePins doublePins) {
        this.doublePins = doublePins;
    }

    DoublePins getDoublePins() {
        return doublePins;
    }

    @Override
    public State bowl(DownPin secondBowl) {
        throw new CanNotBowlException();
    }

    @Override
    public Score calculate(Score prevScore) {
        int indexOfDownpin = DoublePins.FIRST;
        while (prevScore.hasCountLeft()) {
            Score score = Score.of(doublePins.get(indexOfDownpin++));
            prevScore = prevScore.calculate(score);
        }
        return prevScore;
    }

    @Override
    public boolean isFinished() {
        return TRUE;
    }
}