package domain.state;

public class ClosedFrameException extends RuntimeException {

    ClosedFrameException() {
        super("이미 종료된 프레임입니다.");
    }
}
