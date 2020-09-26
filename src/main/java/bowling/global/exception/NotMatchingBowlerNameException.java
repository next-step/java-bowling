package bowling.global.exception;

public class NotMatchingBowlerNameException extends IllegalArgumentException {

    public NotMatchingBowlerNameException(String message) {
        super(message);
    }

}
