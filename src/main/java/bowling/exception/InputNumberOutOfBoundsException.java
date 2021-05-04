package bowling.exception;

public final class InputNumberOutOfBoundsException extends RuntimeException {

    private final String MESSAGE_FORMAT = "맞은 갯수 ( %s ) 는 사용할 수 없는 갯수 입니다.";

    private final int count;

    public InputNumberOutOfBoundsException(final int count) {
        this.count = count;
    }

    @Override
    public String getMessage() {
        return String.format(MESSAGE_FORMAT, count);
    }
}
