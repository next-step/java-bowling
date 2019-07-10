package bowling.model.frame.state;

import bowling.model.Pins;
import bowling.model.frame.State;

import static bowling.model.frame.state.Gutter.PRINT_SYMBOL_OF_GUTTER;

public class Spare implements State {

    private Pins first;

    private Spare(Pins first) {
        this.first = first;
    }

    public static State valueOf(Pins first) {
        return new Spare(first);
    }

    @Override
    public State bowl(Pins pins) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public String printResult() {
        String formatOfNumber = first.isGutter() ? PRINT_SYMBOL_OF_GUTTER : first.toString();
        return String.format("   %s|/   ", formatOfNumber);
    }
}
