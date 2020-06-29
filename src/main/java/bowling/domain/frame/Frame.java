package bowling.domain.frame;

public interface Frame {
    void bowl(int downPin);
    Frame next();
    boolean isLastTryAtFrame();
    String printFrameResult();
}
