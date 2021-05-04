package bowling.exception;

public final class NoMoreFinishActionsException extends RuntimeException {

    private final String message = "현재 상태에서는 프레임이 완료되었는지 확인 할 수 없습니다.";

    @Override
    public final String getMessage() {
        return message;
    }
}
