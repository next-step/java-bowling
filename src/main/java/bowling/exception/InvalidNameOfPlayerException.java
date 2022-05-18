package bowling.exception;

public class InvalidNameOfPlayerException extends RuntimeException {
    private static final String MESSAGE = "ERROR] 플레이어 이름은 영문 3글자여야 합니다(현재: %s).";

    public InvalidNameOfPlayerException(String name) {
        super(String.format(MESSAGE, name));
    }
}
