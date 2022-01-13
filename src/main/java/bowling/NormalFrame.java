package bowling;

import bowling.state.Throwing;
import bowling.state.running.Ready;

public class NormalFrame {

    private final Throwing state;

    private NormalFrame(Throwing state) {
        this.state = state;
    }

    public static NormalFrame first() {
        return new NormalFrame(new Ready());
    }
}
