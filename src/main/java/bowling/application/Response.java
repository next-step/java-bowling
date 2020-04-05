package bowling.application;

import bowling.domain.frame.Bowling;
import bowling.domain.state.State;

import java.util.Collections;
import java.util.List;

public class Response {

    private Bowling bowling;

    public Response(Bowling bowling) {
        this.bowling = bowling;
    }

    public List<State> getState() {
        return Collections.unmodifiableList(bowling.getStates());
    }

    public String getName() {
        return bowling.getName();
    }
}
