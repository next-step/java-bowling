package bowling.model.frame;

import bowling.model.Score;
import bowling.model.state.States;

import java.util.Optional;

public abstract class Frame implements Comparable<Frame> {
    protected final FrameNumber frameNumber;
    protected final States states = new States();

    Frame(FrameNumber frameNumber){
        this.frameNumber = frameNumber;
    }

    public abstract Frame bowling(int fallenPins);

    protected Score addScore(Score score) {
        return states.calculate(score);
    }

    public abstract Optional<String> getScore();

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
