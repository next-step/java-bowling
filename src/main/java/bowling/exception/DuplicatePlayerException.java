package bowling.exception;

public class DuplicatePlayerException extends IllegalArgumentException {

    public DuplicatePlayerException(String s) {
        super(s);
    }
}
