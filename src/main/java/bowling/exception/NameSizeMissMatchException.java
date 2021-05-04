package bowling.exception;

public final class NameSizeMissMatchException extends RuntimeException {

    private final String MESSAGE_FORMAT = "( %s )은 영문 3글자 또는 그 이하가 아닙니다.";

    public NameSizeMissMatchException(final String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return String.format(MESSAGE_FORMAT, super.getMessage());
    }
}
