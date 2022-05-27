package bowling.domain.exception;

public class CannotPitchException extends RuntimeException{
    private static final String CANNOT_PITCH_EXCEPTION = "더이상 투구를 할 수 없는 상태입니다.";
    public CannotPitchException(){
        super(CANNOT_PITCH_EXCEPTION);
    }
}
