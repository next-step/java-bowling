package bowling.application;

import bowling.domain.frame.Bowling;
import bowling.domain.frame.Frame;
import bowling.domain.state.State;

import java.util.LinkedList;

public class Response {

    private Bowling bowling;

    public Response(Bowling bowling) {
        this.bowling = bowling;
    }

    public LinkedList<Frame> getState() {
        return new LinkedList<>(bowling.getFrames());
    }

    public String getName() {
        return bowling.getName();
    }

    public int getFrameNumberLast() {
        return 1;
    }

    public boolean isEnd() {
        return true;
    }
}
