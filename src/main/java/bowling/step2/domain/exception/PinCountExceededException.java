package bowling.step2.domain.exception;

public class PinCountExceededException extends IllegalArgumentException {
    private static final String PIN_COUNT_EXCEEDED_EXCEPTION_MESSAGE = "핀 최대 개수를 초과하였습니다. 다시 입력해주세요.";
    
    public PinCountExceededException() {
        super(PIN_COUNT_EXCEEDED_EXCEPTION_MESSAGE);
    }
}
