package bowling.domain;

import java.util.ArrayList;
import java.util.Objects;

import static bowling.controller.BowlingGameController.MAX_FRAME_NO;

public class Frame {
    private final FrameType frameType;
    private final Pitches pitches;

    public Frame(int frameNo) {
        this.frameType = FrameFactory.typeFactory(frameNo, MAX_FRAME_NO);
        this.pitches = new Pitches(new ArrayList<>());
    }

    public Frame pitch(int point) {
        pitches.add(point);
        return this;
    }

    public Frame pitch2(int point) {
        frameType.pitch(point);
        return this;
    }

    public boolean isContinue() {
        return frameType.isContinue(pitches);
    }

    public boolean isContinue2() {
        return frameType.isContinue();
    }

    public int count() {
        return pitches.count();
    }

    public int sum() {
        return pitches.sum();
    }

    public Pitches pitches() {
        return pitches;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Frame frame = (Frame) o;
        return Objects.equals(frameType, frame.frameType) && Objects.equals(pitches, frame.pitches);
    }

    @Override
    public int hashCode() {
        return Objects.hash(frameType, pitches);
    }
}
