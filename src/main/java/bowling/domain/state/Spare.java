package bowling.domain.state;

import bowling.domain.Pins;
import bowling.exception.NextPitchingException;
import bowling.view.InputView;

import java.util.List;

public class Spare implements State {
    private static final String MESSAGE = "스페어";

    private final Pins firstPins;
    private final Pins secondPins;

    public Spare(Pins firstPins, Pins secondPins) {
        this.firstPins = firstPins;
        this.secondPins = secondPins;
    }

    @Override
    public State nextPitch(int pins) {
        throw new NextPitchingException(MESSAGE);
    }

    @Override
    public State lastPitch(int pins) {
        throw new NextPitchingException(MESSAGE);
    }

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public List<State> lastSpare(List<State> list, State state) {
        state = StateFactory.last(InputView.inputPins());
        list.add(state);
        return list;
    }

    @Override
    public int firstPins() {
        return firstPins.getFalledPins();
    }

    @Override
    public int secondPins() {
        return secondPins.getFalledPins();
    }
}
