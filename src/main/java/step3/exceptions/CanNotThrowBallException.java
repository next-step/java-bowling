package step3.exceptions;

public class CanNotThrowBallException extends IllegalArgumentException {

    private static final String CAN_NOT_THROW_BALL = "finish 상태에서는 추가적으로 볼링공을 던질 수 없습니다.";

    public CanNotThrowBallException() {
        super(CAN_NOT_THROW_BALL);
    }
}
