package bowling.domain;

public class CannotGetCurrentGameException extends RuntimeException {
    public static final String MESSAGE = "현재 볼링 게임을 가져오지 못 했습니다.";

    public CannotGetCurrentGameException() {
        super(MESSAGE);
    }
}
