package bowling.domain.exception;

public class NameLengthException extends IllegalArgumentException{
    private static final String MESSAGE = "이름의 길이는 최대 3글자입니다.";
    public NameLengthException() {
        super(MESSAGE);
    }
}
