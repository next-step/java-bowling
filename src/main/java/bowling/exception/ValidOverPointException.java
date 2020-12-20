package bowling.exception;

public class ValidOverPointException extends IllegalArgumentException{

    public static final String ValidOverPoint = "포인트 갯수 합을 초과하셨습니다.";

    public ValidOverPointException() {
        super(ValidOverPoint);
    }
}
