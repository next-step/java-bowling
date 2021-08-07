package bowling.domain.state;

public class State {

    private State() {
    }

    public static State of() {
        return new State();
    }

}
