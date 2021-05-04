package bowling.domain.exception;

public class IllegalPlayerName extends RuntimeException {
    public IllegalPlayerName() {
        super();
    }

    public IllegalPlayerName(String message) {
        super(message);
    }
}
