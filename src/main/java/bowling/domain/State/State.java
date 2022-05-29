package bowling.domain.State;

public abstract class State {
    public abstract State bowl(Pin pin);

    public static Ready ready() {
        return new Ready();
    }

    public abstract boolean isDone();

    public abstract String toString();

    public abstract String toSimpleString();

    public boolean isMiss() {
        return false;
    }

    public boolean isSecond() {
        return false;
    }
}
