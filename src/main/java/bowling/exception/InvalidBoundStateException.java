package bowling.exception;

public class InvalidBoundStateException extends IllegalStateException {
    private static final String MESSAGE = "요청한 State 가 존재하지 않습니다. index= %d";

    public InvalidBoundStateException(int index) {
        super(String.format(MESSAGE, index));
    }
}
