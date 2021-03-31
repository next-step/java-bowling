package bowling.domain;

import java.util.List;

public class Frame {

    private final FrameNumber frameNumber;

    private final PinCounts pintCounts;

    public Frame(FrameNumber frameNumber, PinCounts pintCounts) {
        this.frameNumber = frameNumber;
        this.pintCounts = pintCounts;
    }

    public Frame(int number) {
        this(new FrameNumber(number), new PinCounts());
    }

    public void addPintCount(int pinCount) {
        pintCounts.add(pinCount);
    }

    public List<PinCount> pinCounts() {
        return pintCounts.counts();
    }

    public FrameNumber number() {
        return frameNumber;
    }

    public int totalPinCounts() {
        return pintCounts.totalCount();
    }

    public int pinCountsSize() {
        return pintCounts.size();
    }


}
