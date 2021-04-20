package bowling.exception;

public class PinsOutOfSizeException extends IllegalArgumentException {
    public PinsOutOfSizeException() {
    }
    public PinsOutOfSizeException(String s) {
        super(s);
    }
}
