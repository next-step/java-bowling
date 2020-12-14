package bowling.model.frame;

import bowling.model.state.States;

public abstract class Frame implements Comparable<Frame> {
    protected FrameNumber frameNumber;
    protected States states = new States();

    public abstract Frame bowling(int fallenPins);

    public boolean isFinished() {
        return states.isFinished();
    }

    public boolean isStartFrame() {
        return states.isStart();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Frame) {
            return frameNumber.equals(((Frame) obj).frameNumber);
        }

        return false;
    }

    @Override
    public int hashCode() {
        return frameNumber.hashCode();
    }

    @Override
    public int compareTo(Frame o) {
        return frameNumber.compareTo(o.frameNumber);
    }

    @Override
    public String toString() {
        return states.toString();
    }
}
