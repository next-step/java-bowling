package bowling.application;

import bowling.domain.frame.Bowling;
import bowling.domain.player.Player;
import bowling.domain.state.State;

import java.util.LinkedList;

public class BowlingService {

    private LinkedList<State> states;

    public BowlingService() {
        this.states = new LinkedList<>();
    }

    public Bowling bowl(Request request) {
        Bowling bowling = new Bowling(states, new Player(request.getName()));
        bowling.bowl(request.getFallenPins());
        return bowling;
    }
}
