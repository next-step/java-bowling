package bowling.exception;

public final class ScoreListNullPointerException extends RuntimeException {

    private final String MESSAGE = "List<Score> 인스턴스가 null 입니다.";

    @Override
    public String getMessage() {
        return MESSAGE;
    }
}
