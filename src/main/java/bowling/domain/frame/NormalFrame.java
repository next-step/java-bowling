package bowling.domain.frame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NormalFrame implements Frame {

    private final List<DownedPin> downedPins;

    public NormalFrame() {
        this.downedPins = new ArrayList<>();
    }

    @Override
    public void record(int numDownedPins) {
        FrameStatus.record(downedPins, numDownedPins);
    }

    @Override
    public boolean isEnd() {
        return FrameStatus.isEnd(downedPins);
    }

    public FrameStatus decideStatus() {
        return FrameStatus.decideStatus(downedPins);
    }

    public List<DownedPin> getDownedPins() {
        return Collections.unmodifiableList(downedPins);
    }
}
