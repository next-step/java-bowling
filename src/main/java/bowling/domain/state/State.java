package bowling.domain.state;

public interface State {

    static State ready() {
        return Ready.initialize();
    }

    State bowl(final PinCount hitCount);

    boolean isFinish();

    boolean isAllPinClear();

    int size();

    int firstCount();

    int secondCount();
}
