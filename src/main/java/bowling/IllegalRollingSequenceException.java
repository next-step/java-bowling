package bowling;

public class IllegalRollingSequenceException extends IllegalArgumentException{
    public IllegalRollingSequenceException(String errorMessage) {
        super(errorMessage);
    }
}
