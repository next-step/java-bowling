package bowling.domain.point;

public class PointOutOfRangeException extends IllegalArgumentException {

    public PointOutOfRangeException(String msg){
        super(msg);
    }
}
