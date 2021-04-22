package bowling.domain.frame;

public interface Frame {
    void bowl(int pinCount);
    Frame next();
    boolean isDone();
}
