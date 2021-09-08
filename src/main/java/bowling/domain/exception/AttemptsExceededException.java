package bowling.domain.exception;

public class AttemptsExceededException extends RuntimeException {

    public AttemptsExceededException() {
        super("해당 프레임의 시도 횟수를 초과했습니다.");
    }
}
