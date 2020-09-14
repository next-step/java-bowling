package bowling.state;

public interface State {

    static State bowl(int value) {
        if (Pin.isStrike(value)) {
            return new Strike();
        }

        return FirstState.of(value);
    }

    String expression();
}
