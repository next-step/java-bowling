package bowling.exception;

public class LimitException extends IllegalArgumentException{

    public LimitException(String message) {
        super(message);
    }
}
