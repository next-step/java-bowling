package bowling.exception;

public final class InvalidPlayerCountException extends RuntimeException {

    private final String MESSAGE_FORMAT = "PlayerCount 에 대해 알맞지 않은 크기 (%s)가 입력 되었습니다.";

    public InvalidPlayerCountException(final int message) {
        this(String.valueOf(message));
    }

    public InvalidPlayerCountException(final String message) {
        super(message);
    }

    @Override
    public final String getMessage() {
        return String.format(MESSAGE_FORMAT, super.getMessage());
    }
}
