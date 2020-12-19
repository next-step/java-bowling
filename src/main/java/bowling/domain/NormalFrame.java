package bowling.domain;

import java.util.ArrayList;
import java.util.List;

public class NormalFrame {

    private static final int MAXIMUM_TRIES_PER_NORMAL_FRAME = 2;

    private final List<DownedPin> tries;

    public NormalFrame() {
        this.tries = new ArrayList<>();
    }

    public void record(DownedPin downedPin) {
        if (tries.size() == 0) {
            tries.add(downedPin);
            return;
        }

        tries.add(tries.get(0).fromSubordinateTry(downedPin));
    }

    public boolean isEnd() {
        return (tries.get(0).isStrike() || tries.size() == MAXIMUM_TRIES_PER_NORMAL_FRAME);
    }
}
