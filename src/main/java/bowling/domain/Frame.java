package bowling.domain;

public interface Frame {
    void bowl(int downPin);
    Frame next();
    boolean isLastTryAtFrame();
}
