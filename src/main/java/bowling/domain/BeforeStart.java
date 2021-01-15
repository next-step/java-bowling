package bowling.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class BeforeStart implements PinMarkerState {

    @Override
    public boolean isStarted() {
        return false;
    }

    @Override
    public boolean isCompleted() {
        return false;
    }

    @Override
    public List<PinMarkSymbol> toSymbols() {
        return Collections.unmodifiableList(new ArrayList<>());
    }
}
