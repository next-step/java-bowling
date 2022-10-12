package bowling.step2.domain.exception;

public class CountOfFallenPinsException extends IllegalArgumentException {
    private static final String COUNT_OF_FALLEN_PINS_EXCEPTION_MESSAGE = "투구 결과를 잘못 입력하였습니다. 다시 입력해 주세요.";
    
    public CountOfFallenPinsException() {
        super(COUNT_OF_FALLEN_PINS_EXCEPTION_MESSAGE);
    }
}
