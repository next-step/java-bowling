package bowling.exception;

public final class NameIncludeOtherLanguagesException extends RuntimeException {

    private final String MESSAGE_FORMAT = "( %s )는 영문 글자가 아닌 다른 글자도 포함되어 있습니다.";

    public NameIncludeOtherLanguagesException(final String name) {
        super(name);
    }

    @Override
    public String getMessage() {
        return String.format(MESSAGE_FORMAT, super.getMessage());
    }
}
