package bowling.exception;

public final class PinsNullPointerException extends RuntimeException {

    private final String MESSAGE = "Pins 인스턴스가 null 입니다.";

    @Override
    public String getMessage() {
        return MESSAGE;
    }
}
