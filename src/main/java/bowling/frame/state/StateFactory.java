package bowling.frame.state;

public class StateFactory {
    public static State ready() {
        return new Ready();
    }

    public static State firstBowl(final int countOfPin) {
        return ready().bowl(countOfPin);
    }
}
