package bowling.exception;

public final class InvalidBonusCountSizeException extends RuntimeException {

    private final String MESSAGE = "알맞는 보너스 횟수에 대한 크기가 아닙니다.";

    @Override
    public String getMessage() {
        return MESSAGE;
    }
}
