package bowling.model.frame;

import bowling.model.state.Start;
import bowling.model.state.State;

public abstract class Frame implements Comparable<Frame> {
    protected FrameNumber frameNumber;
    protected State state = new Start();

    public abstract Frame bowling(int fallenPins);

    public boolean isFinished(){
        return state.isFinished();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Frame){
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
        return FrameNumber.compare(this.frameNumber, o.frameNumber);
    }
}
