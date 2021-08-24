package bowling.domain.state;

public class StateFactory {
    public static State run() {
        return new Ready();
    }

    public static State first(int pins) {
        return run().nextPitch(pins);
    }
}
