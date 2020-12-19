package bowling.domain;

import java.util.ArrayList;
import java.util.List;

public class NormalFrame {

    private static final int MAXIMUM_TRIES_PER_NORMAL_FRAME = 2;

    private final List<DownedPinPerTry> tries;

    public NormalFrame() {
        this.tries = new ArrayList<>();
    }

    public void record(DownedPinPerTry downedPinPerTry) {
        if (tries.size() == 0) {
            tries.add(downedPinPerTry);
            return;
        }

        tries.add(tries.get(0).fromFirstTry(downedPinPerTry));
    }

    public boolean isEnd() {
        return (tries.get(0).isStrike() || tries.size() == MAXIMUM_TRIES_PER_NORMAL_FRAME);
    }
}
