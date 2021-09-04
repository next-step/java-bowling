package bowling.exception;

public class IllegalPayerNumberException extends RuntimeException {
    public IllegalPayerNumberException(int number) {
        super("플레이어의 숫자는 0보다 큰 숫자여야 합니다. ===" + number);
    }
}
