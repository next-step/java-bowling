package bowling.exception;

public final class NameNullPointerException extends RuntimeException {

    private final String MESSAGE = "Name 인스턴스가 null 입니다.";

    @Override
    public String getMessage() {
        return MESSAGE;
    }
}

