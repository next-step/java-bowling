package bowling.exception;

public class NameLengthException extends IllegalArgumentException {

    public NameLengthException(String s) {
        super(s);
    }
}
