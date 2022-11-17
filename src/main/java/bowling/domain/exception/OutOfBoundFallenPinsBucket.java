package bowling.domain.exception;

public class OutOfBoundFallenPinsBucket extends RuntimeException {

    public OutOfBoundFallenPinsBucket() {
        super("해당 프레임에 허용되는 턴의 핀들이 아닙니다.");
    }

}
