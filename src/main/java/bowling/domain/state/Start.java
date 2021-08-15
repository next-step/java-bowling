package bowling.domain.state;

import bowling.domain.pin.Pins;

import java.util.Collections;
import java.util.List;

public class Start extends State {

    private Start() {
        resultState = ResultState.NONE;
        progressState = ProgressState.START;
    }

    public static Start of() {
        return new Start();
    }

    @Override
    public State hitPins(Pins pins) {
        if (pins.isAllHit()) {
            return Strike.of();
        }

        return Progress.of(pins);
    }

    public boolean isStart() {
        return true;
    }

    @Override
    public List<Integer> getHitPins() {
        return Collections.emptyList();
    }

}
