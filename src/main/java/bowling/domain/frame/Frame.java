package bowling.domain.frame;

public interface Frame {

    boolean isEnd();

    Frame next();

    int number();

}
