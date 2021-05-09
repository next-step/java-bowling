package bowling.exception;

public final class StringNullPointerException extends RuntimeException {

    private final String MESSAGE = "String 인스턴스가 null 입니다.";

    @Override
    public String getMessage() {
        return MESSAGE;
    }
}
