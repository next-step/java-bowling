package bowling.domain.frame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NormalFrame implements Frame {

    private final List<DownedPin> tries;

    public NormalFrame() {
        this.tries = new ArrayList<>();
    }

    @Override
    public void record(DownedPin currentTry) {
        if (getCurrentProgress() == NormalFrameProgress.ON_FIRST_PITCH) {
            tries.add(currentTry);
            return;
        }

        tries.add(tries.get(0).fromSubordinateTry(currentTry));
    }

    @Override
    public boolean isEnd() {
        return getFrameStatus() == FrameStatus.STRIKE || getCurrentProgress() == NormalFrameProgress.END;
    }

    private NormalFrameProgress getCurrentProgress() {
        return NormalFrameProgress.getProgress(Collections.unmodifiableList(tries));
    }

    @Override
    public int getNumThrown() {
        return tries.size();
    }

    public FrameStatus getFrameStatus() {
        return FrameStatus.getStatus(Collections.unmodifiableList(tries));
    }

    @Override
    public String printStatus() {
        FrameStatus status = getFrameStatus();
        String interpretedFrame = status.interpretFrame(tries);

        if (status == FrameStatus.STRIKE) {
            return interpretedFrame;
        }

        return interpretedFrame;
    }
}
