package bowling.domain;

import bowling.domain.pitch.Pitch;

import java.util.LinkedList;
import java.util.List;

public class FinalFrame implements Frame {

    private final FrameNumber frameNumber;
    private List<Pitch> pitches = new LinkedList<>();

    public FinalFrame() {
        frameNumber = new FrameNumber(BowlingGame.MAX_FRAME_SIZE);
    }

    @Override
    public Frame pitch(int countOfPins) {
        return null;
    }

    @Override
    public boolean isEnd() {
        return false;
    }

    @Override
    public String result() {
        return null;
    }

    @Override
    public Frame next() {
        return null;
    }
}
