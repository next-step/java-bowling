package bowling.domain.state;

public class StateFactory {
    public static State run() {
        return new Ready();
    }

    public static State first(int pins) {
        return run().nextPitch(pins);
    }

    public static State last(int pins) {
        return run().lastPitch(pins);
    }

}
