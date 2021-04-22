package bowling.exception;

public class CanNotBonusException extends IndexOutOfBoundsException {
    public CanNotBonusException() {
        super();
    }

    public CanNotBonusException(String message) {
        super(message);
    }
}
