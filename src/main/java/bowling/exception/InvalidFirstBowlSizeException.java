package bowling.exception;

public final class InvalidFirstBowlSizeException extends RuntimeException {

    private final String MESSAGE = "FirstBowl 에 대해 알맞지 않은 크기가 입력 되었습니다.";

    @Override
    public String getMessage() {
        return MESSAGE;
    }
}