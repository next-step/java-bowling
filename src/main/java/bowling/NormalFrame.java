package bowling;

import bowling.state.running.Ready;
import bowling.state.running.Running;

public class NormalFrame {

    private final Running state;

    private NormalFrame(Running state) {
        this.state = state;
    }

    public static NormalFrame first() {
        return new NormalFrame(new Ready());
    }
}
