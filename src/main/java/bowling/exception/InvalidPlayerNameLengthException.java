package bowling.exception;

public class InvalidPlayerNameLengthException extends RuntimeException {

    private static final String MESSAGE = "플레이어의 이름의 길이는 1 이상 3 이하여야 합니다. (현재 길이: %d)";

    public InvalidPlayerNameLengthException(int nameLength) {
        super(String.format(MESSAGE, nameLength));
    }
}
