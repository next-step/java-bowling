package bowling.application;

import bowling.domain.frame.Bowling;
import bowling.domain.player.Player;
import bowling.domain.state.State;

import java.util.LinkedList;
import java.util.Objects;

public class BowlingService {

    private LinkedList<State> states;

    public BowlingService() {
        this.states = new LinkedList<>();
    }

    public Bowling bowl(Request request) {
        Bowling bowling = new Bowling(states, new Player(request.getName()));
        if (Objects.isNull(request.getPin())) {
            return bowling;
        }
        bowling.bowl(request.getPin());
        return bowling;
    }
}
