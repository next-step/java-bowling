package bowling.domain.frame;

public interface Frame {

    Frame next(int score);

    boolean isDone();
}
