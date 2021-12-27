package bowling.exception;

public class PlayerLengthOutOfBoundException extends Exception {

    private static final String MESSAGE = "이름의 길이는 %d 여야 합니다.";

    public PlayerLengthOutOfBoundException(int length) {
        super(String.format(MESSAGE, length));
    }

}
