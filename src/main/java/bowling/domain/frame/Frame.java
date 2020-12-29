package bowling.domain.frame;

import java.util.ArrayList;
import java.util.List;

public class Frame {

    private final List<DownedPin> downedPins;

    public Frame() {
        this.downedPins = new ArrayList<>();
    }

    public void record(int numDownedPins) {
        FrameStatus.record(downedPins, numDownedPins);
    }

    public boolean isEnd() {
        return FrameStatus.isEnd(downedPins);
    }

    public FrameStatus decideStatus() {
        return FrameStatus.decideStatus(this.downedPins);
    }
}
