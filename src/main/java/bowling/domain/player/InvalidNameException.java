package bowling.domain.player;

public class InvalidNameException extends IllegalArgumentException {

    public InvalidNameException(String msg){
        super(msg);
    }
}
