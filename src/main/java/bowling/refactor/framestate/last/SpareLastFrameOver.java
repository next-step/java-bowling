package bowling.refactor.framestate.last;

import bowling.refactor.framestate.State;

import java.util.ArrayList;
import java.util.List;

public class SpareLastFrameOver implements State{

    private List<Integer> pins;

    private SpareLastFrameOver(final List<Integer> pins) {
        this.pins = new ArrayList<>(pins);
    }

    public static SpareLastFrameOver newInstance(final List<Integer> pins) {
        return new SpareLastFrameOver(pins);
    }

    @Override
    public State Bowl(int countOfPin) {
        throw new IllegalStateException("No more bowl.");
    }
}
