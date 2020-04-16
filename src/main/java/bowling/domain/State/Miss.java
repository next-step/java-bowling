package bowling.domain.State;

import bowling.domain.Rolls;

public class Miss extends Finished {
    private final Rolls rolls;

    public Miss(Rolls rolls) {
        this.rolls = rolls;
    }

    @Override
    public String getDisplayText() {
        return null;
    }
}
