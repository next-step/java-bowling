package bowling.exception;

public final class NoMoreBowlActionsException extends RuntimeException {

    private final String message = "현재 상태에서는 더 이상 투구를 할 수 없습니다.";

    @Override
    public final String getMessage() {
        return message;
    }
}
