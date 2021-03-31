package bowling.domain;

import java.util.ArrayList;
import java.util.List;

public class Frame {

    private final FrameNumber frameNumber;

    private final List<Integer> pintCounts = new ArrayList<>();

    public Frame(int number) {
        this.frameNumber = new FrameNumber(number);
    }

    public void addPintCount(int pinCount) {
        pintCounts.add(pinCount);
    }

    public List<Integer> pinCounts() {
        return pintCounts;
    }

    public int number() {
        return frameNumber.number();
    }
}
