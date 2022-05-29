package bowling.domain.exception;

public class OutOfPinCountException extends RuntimeException{
    private static final String OUT_OF_PIN_COUNT_MESSAGE = "한 프레임에 쓰러트릴 수 있는 투구수 는 최대 10개입니다.";
    public OutOfPinCountException(){
        super(OUT_OF_PIN_COUNT_MESSAGE);
    }
}
