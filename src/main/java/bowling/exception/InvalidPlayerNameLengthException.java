package bowling.exception;

public class InvalidPlayerNameLengthException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private static final String INVALID_LENGTH = "플레이어 이름은 3글자만 가능합니다 -> %s";

    public InvalidPlayerNameLengthException(String name) {
        super(String.format(INVALID_LENGTH, name));
    }

}
