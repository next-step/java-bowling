package bowling.exception;

public class IllegalPayerNameException extends RuntimeException {
    public IllegalPayerNameException(String name) {
        super("플레이어의 이름은 영문 3글자여야 합니다. ===" + name);
    }
}
