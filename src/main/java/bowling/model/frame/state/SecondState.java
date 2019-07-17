package bowling.model.frame.state;

import bowling.model.DownPin;
import bowling.model.frame.State;

import java.util.List;

import static java.lang.Boolean.TRUE;

public abstract class SecondState implements State {

    private static final int FIRST = 0;
    private static final int SECOND = 1;

    private final List<DownPin> doublePins;

    SecondState(List<DownPin> doublePins) {
        this.doublePins = doublePins;
    }

    List<DownPin> getDoublePins() {
        return doublePins;
    }

    DownPin getFirstDownPins() {
        return doublePins.get(FIRST);
    }

    DownPin getSecondDownPins() {
        return doublePins.get(SECOND);
    }

    @Override
    public State bowl(DownPin secondBowl) {
        throw new CanNotBowlException();
    }

    @Override
    public Score calculate(Score prevScore) {
        for (DownPin downPin : doublePins) {
            Score score = Score.of(downPin);
            prevScore = prevScore.calculate(score);
        }

        return prevScore;
    }

    @Override
    public boolean isFinished() {
        return TRUE;
    }

    String getFirstSymbol() {
        DownPin firstDownPins = getFirstDownPins();
        if (Gutter.isMatch(firstDownPins)) {
            return Gutter.SYMBOL;
        }

        return firstDownPins.toString();
    }
}