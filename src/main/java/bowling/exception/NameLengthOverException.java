package bowling.exception;

public class NameLengthOverException extends IllegalArgumentException {
    private static final String MESSAGE = "이름의 길이가 3글자를 넘었습니다. : %s";

    public NameLengthOverException(String causativeValue) {
        super(String.format(MESSAGE, causativeValue));
    }
}
