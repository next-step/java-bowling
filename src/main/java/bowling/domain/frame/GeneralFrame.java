package bowling.domain.frame;

import bowling.domain.state.Preparation;
import bowling.domain.state.State;

public class GeneralFrame extends Frame {

    private GeneralFrame(State state) {
        super(state);
    }

    public static GeneralFrame init() {
        return new GeneralFrame(Preparation.instance());
    }
}
