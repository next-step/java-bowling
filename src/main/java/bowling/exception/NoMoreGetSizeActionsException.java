package bowling.exception;

public final class NoMoreGetSizeActionsException extends RuntimeException {

    private final String message = "현재 상태에서는 사이즈 값을 알 수 없습니다.";

    @Override
    public final String getMessage() {
        return message;
    }
}
