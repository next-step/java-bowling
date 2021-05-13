package bowling.exception;

public final class NoMoreBowlException extends RuntimeException {

    private final String MESSAGE = "현재 상태에서는 더 이상 볼을 던질 수 없습니다.";

    @Override
    public String getMessage() {
        return MESSAGE;
    }
}
