package bowling.domain.exception;

public class AttemptsExceededException extends RuntimeException {

    public AttemptsExceededException() {
        super("한 프레임에 시도 회수는 2번을 초과할 수 없습니다.");
    }
}
