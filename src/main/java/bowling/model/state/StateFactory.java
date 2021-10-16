package bowling.model.state;

public class StateFactory {
    public static State ready() {
        return new Ready();
    }
}
