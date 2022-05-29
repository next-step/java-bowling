package bowling.domain.exception;

public class CannotCalculateException extends RuntimeException{
    private static final String CANNOT_CALCULATE_EXCEPTION = "점수를 생성할 수 없습니다.";
    public CannotCalculateException(){
        super(CANNOT_CALCULATE_EXCEPTION);
    }
}
