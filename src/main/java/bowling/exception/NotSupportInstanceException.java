package bowling.exception;

public class NotSupportInstanceException extends RuntimeException {

    private static final String MESSAGE = "인스턴스를 생성을 지원하지 않습니다.";

    public NotSupportInstanceException() {
        super(MESSAGE);
    }
}
