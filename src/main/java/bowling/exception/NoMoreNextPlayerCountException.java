package bowling.exception;

public class NoMoreNextPlayerCountException extends RuntimeException {

    private final String MESSAGE = "PlayerCount 의 다음 순서로 넘어갈 수 없습니다.";

    @Override
    public String getMessage() {
        return MESSAGE;
    }
}
