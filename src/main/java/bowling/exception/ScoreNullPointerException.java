package bowling.exception;

public final class ScoreNullPointerException extends RuntimeException {

    private final String MESSAGE = "Score 인스턴스가 null 입니다.";

    @Override
    public String getMessage() {
        return MESSAGE;
    }
}