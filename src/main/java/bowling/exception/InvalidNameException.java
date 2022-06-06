package bowling.exception;

public class InvalidNameException extends IllegalArgumentException {

    private static final String MESSAGE = "영어 3글자로 입력해야 합니다.";

    public InvalidNameException() {
        super(MESSAGE);
    }
}
