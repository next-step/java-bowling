package bowling.step2.domain.exception;

public class DisplayException extends IllegalArgumentException {
    private static final String DISPLAY_EXCEPTION_MESSAGE = "해당 프레임이 끝나지 않은 상태입니다.";
    
    public DisplayException() {
        super(DISPLAY_EXCEPTION_MESSAGE);
    }
}
