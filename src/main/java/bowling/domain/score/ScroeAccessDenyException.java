package bowling.domain.score;

public class ScroeAccessDenyException extends IllegalArgumentException {

    public ScroeAccessDenyException(String msg){
        super(msg);
    }
}
