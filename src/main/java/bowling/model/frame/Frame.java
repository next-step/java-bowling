package bowling.model.frame;

import bowling.model.Pins;
import bowling.model.Score;
import bowling.model.state.States;

import java.util.Optional;

public abstract class Frame implements Comparable<Frame> {
    protected final FrameNumber frameNumber;
    protected final States states = new States();

    Frame(FrameNumber frameNumber) {
        this.frameNumber = frameNumber;
    }

    public abstract Frame bowling(Pins pins);

    protected abstract Score addScore(Score score);

    public abstract Optional<Integer> getScore();

    public boolean isFinished() {
        return states.isFinished();
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

    public boolean isNewFrame() {
        return states.isEmpty();
    }
}
