package bowling.domain.status;

public class CanNotThrowBallException extends IllegalArgumentException {

    public CanNotThrowBallException(String msg){
        super(msg);
    }
}
