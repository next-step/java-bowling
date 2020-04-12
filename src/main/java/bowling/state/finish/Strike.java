package bowling.state.finish;

import bowling.state.State;

public class Strike extends Finished {

    private Strike() {
    }

    public static State of() {
        return new Strike();
    }

    @Override
    public String view() {
        return STRIKE;
    }
}
