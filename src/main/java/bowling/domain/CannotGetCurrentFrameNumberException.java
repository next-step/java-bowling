package bowling.domain;

public class CannotGetCurrentFrameNumberException extends RuntimeException {
    public static final String MESSAGE = "현재 프레임 번호를 가져오지 못 했습니다.";

    public CannotGetCurrentFrameNumberException() {
        super(MESSAGE);
    }
}
