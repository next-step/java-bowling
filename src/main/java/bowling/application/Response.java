package bowling.application;

import bowling.domain.frame.Bowling;
import bowling.domain.state.State;

import java.util.LinkedList;

public class Response {

    private Bowling bowling;

    public Response(Bowling bowling) {
        this.bowling = bowling;
    }

    public LinkedList<State> getState() {
        return new LinkedList<>(bowling.getStates());
    }

    public String getName() {
        return bowling.getName();
    }

    public int getFrameNumberLast() {
        return bowling.getFrameNumberLast();
    }

    public boolean isEnd() {
        return bowling.isEnd();
    }
}
