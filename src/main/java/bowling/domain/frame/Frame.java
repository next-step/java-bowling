package bowling.domain.frame;

import java.util.ArrayList;
import java.util.List;

public class Frame {

    private static final int MAXIMUM_TRIES_PER_FRAME = 2;

    private final List<DownedPin> downedPins;

    public Frame() {
        this.downedPins = new ArrayList<>();
    }

    public void record(int counts) {
        if (downedPins.size() == 1) {
            downedPins.add(downedPins.get(0).fromPreviousPitch(counts));
            return;
        }

        downedPins.add(DownedPin.fromNumber(counts));
    }

    public boolean isEnd() {
        if (downedPins.size() == 1 && downedPins.get(0).isTen()) {
            return true;
        }

        return downedPins.size() == MAXIMUM_TRIES_PER_FRAME;
    }
}
