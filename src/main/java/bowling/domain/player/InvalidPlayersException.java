package bowling.domain.player;

public class InvalidPlayersException extends IllegalArgumentException {

    public InvalidPlayersException(String msg){
        super(msg);
    }
}
