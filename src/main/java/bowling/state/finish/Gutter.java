package bowling.state.finish;

import bowling.state.State;

public class Gutter extends Finished {

    private Gutter() {
    }

    public static State of() {
        return new Gutter();
    }

    @Override
    public String view() {
        return GUTTER;
    }
}
