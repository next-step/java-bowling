package bowling.exception;

public class IllegalPinNumberException extends RuntimeException {
    public IllegalPinNumberException(int pin) {
        super("볼링핀은 0부터 10까지의 숫자중 하나여야 합니다.====" + pin);
    }
}
