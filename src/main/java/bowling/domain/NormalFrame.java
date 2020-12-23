package bowling.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NormalFrame {

    private static final int MAXIMUM_TRIES_PER_NORMAL_FRAME = 2;

    private final List<DownedPin> tries;

    public NormalFrame() {
        this.tries = new ArrayList<>();
    }

    public void record(DownedPin currentTry) {
        if (tries.size() == 0) {
            tries.add(currentTry);
            return;
        }

        tries.add(tries.get(0).fromSubordinateTry(currentTry));
    }

    public boolean isEnd() {
        return (getFrameStatus() == FrameStatus.STRIKE || tries.size() == MAXIMUM_TRIES_PER_NORMAL_FRAME);
    }

    public FrameStatus getFrameStatus() {
        if (tries.size() == 0) {
            return FrameStatus.getStatus(null, null);
        }

        if (tries.size() == 1) {
            return FrameStatus.getStatus(tries.get(0), null);
        }

        return FrameStatus.getStatus(tries.get(0), tries.get(1));
    }

    public List<DownedPin> getTries() {
        return Collections.unmodifiableList(tries);
    }
}
