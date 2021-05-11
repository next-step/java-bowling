package bowling.exception;

public final class FrameNullPointerException extends RuntimeException {

    private final String MESSAGE = "Frame 인스턴스가 null 입니다.";

    @Override
    public String getMessage() {
        return MESSAGE;
    }
}