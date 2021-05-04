package bowling.exception;

public final class NoMoreIndexActionsException extends RuntimeException {

    private final String message = "현재 상태에서는 인덱스를 확인 할 수 없습니다.";

    @Override
    public final String getMessage() {
        return message;
    }
}
