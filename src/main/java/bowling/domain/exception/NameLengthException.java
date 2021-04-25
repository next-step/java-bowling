package bowling.domain.exception;

public class NameLengthException extends IllegalArgumentException{
    private static final String MESSAGE = "이름은 영문 3글자로 이루어져야 합니다";
    public NameLengthException() {
        super(MESSAGE);
    }
}
