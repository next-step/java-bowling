package bowling.exception;

public class NameEmptyException extends IllegalArgumentException {

    public NameEmptyException(String s) {
        super(s);
    }
}
