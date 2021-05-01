package bowling.domain.state;

import static java.lang.Boolean.TRUE;

public final class Strike extends Finish {

    private Strike() {
    }

    public static final State initialize() {
        return new Strike();
    }

    @Override
    public final boolean isAllPinClear() {
        return TRUE;
    }
}
