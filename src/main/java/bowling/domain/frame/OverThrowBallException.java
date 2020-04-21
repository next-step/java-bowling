package bowling.domain.frame;

public class OverThrowBallException extends IllegalArgumentException {

    public OverThrowBallException(String msg){
        super(msg);
    }
}
