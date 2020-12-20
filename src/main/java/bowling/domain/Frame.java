package bowling.domain;

import java.util.LinkedList;
import java.util.Objects;

public abstract class Frame {

    protected final int BOWLING_PIN_COUNT = 10;
    protected final int MAX_FRAME_COUNT = 10;

    protected int index;
    protected LinkedList<Integer> pitchResults;

    public Frame(int index){
        this.index = index;
        this.pitchResults = new LinkedList<>();
    }

    public int getIndex() {
        return this.index;
    }

    public LinkedList<Integer> getPitchResults() {
        return pitchResults;
    }

    public int sumCurrentPitchResults() {
        return pitchResults.stream().mapToInt(Integer::intValue).sum();
    }

    public abstract void start(PitchStrategy pitchStrategy);

    public abstract boolean isEnd();

    public abstract Frame makeNextFrame(int currentFrameIndex);

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Frame frame = (Frame) o;
        return BOWLING_PIN_COUNT == frame.BOWLING_PIN_COUNT &&
                MAX_FRAME_COUNT == frame.MAX_FRAME_COUNT &&
                index == frame.index &&
                Objects.equals(pitchResults, frame.pitchResults);
    }

    @Override
    public int hashCode() {
        return Objects.hash(BOWLING_PIN_COUNT, MAX_FRAME_COUNT, index, pitchResults);
    }
}
