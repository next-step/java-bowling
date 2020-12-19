package bowling.domain;

import java.util.ArrayList;
import java.util.List;

public class NormalFrame {

    private static final int FIRST_TURN_INDEX = 0;
    private static final int MAXIMUM_TRIES_PER_NORMAL_FRAME = 2;

    private final List<DownedPin> tries;

    public NormalFrame() {
        this.tries = new ArrayList<>();
    }

    public void record(DownedPin currentTry) {
        if (tries.size() == FIRST_TURN_INDEX) {
            tries.add(currentTry);
            return;
        }

        tries.add(tries.get(FIRST_TURN_INDEX).fromSubordinateTry(currentTry));
    }

    public boolean isEnd() {
        return (tries.get(FIRST_TURN_INDEX).isStrike() || tries.size() == MAXIMUM_TRIES_PER_NORMAL_FRAME);
    }
}
