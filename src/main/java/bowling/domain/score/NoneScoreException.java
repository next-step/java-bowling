package bowling.domain.score;

public class NoneScoreException extends IllegalArgumentException {

    public NoneScoreException(String msg){
        super(msg);
    }
}
