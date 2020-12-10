package bowling;

import java.util.LinkedList;
import java.util.List;

public class LastFrame implements Frame {
    public static final int LAST_FRAME_MAX_PITCHING_SIZE = 3;
    private final int index;
    private final LinkedList<Pitching> pitchings;
    private final Frame previousFrame;

    public LastFrame(int index) {
        this.index = index;
        pitchings = new LinkedList<>();
        previousFrame = null;
    }

    public LastFrame(int index, Frame previousFrame) {
        this.index = index;
        pitchings = new LinkedList<>();
        this.previousFrame = previousFrame;
    }

    @Override
    public Frame initNextFrame() {
        throw new IllegalStateException();
    }

    @Override
    public Frame getNextFrame() {
        throw new IllegalStateException();
    }

    @Override
    public void setKnockDownPins(int knockDownPins) {
        if (pitchings.isEmpty()) {
            pitchings.add(Pitching.getPitching(knockDownPins));
            return;
        }

        if (pitchings.size() < LAST_FRAME_MAX_PITCHING_SIZE - 1) {
            Pitching previousPitching = pitchings.getLast();
            pitchings.add(Pitching.getPitching(knockDownPins, previousPitching));
            return;
        }

        if (hasThirdChance()) {
            pitchings.add(Pitching.getPitching(knockDownPins));
            return;
        }

        throw new IllegalMonitorStateException();
    }

    @Override
    public List<Pitching> getStatus() {
        return pitchings;
    }

    @Override
    public boolean isEnd() {
        if (pitchings.size() < LAST_FRAME_MAX_PITCHING_SIZE - 1) {
            return false;
        }

        return !hasThirdChance() || pitchings.size() >= LAST_FRAME_MAX_PITCHING_SIZE;
    }

    private boolean hasThirdChance() {
        return pitchings.contains(Pitching.STRIKE) || pitchings.contains(Pitching.SPARE);
    }
}
