package bowling.domain.frame;

public interface Frame {
    void bowl(int downPin);
    Frame next(int index);
    boolean isLastTryAtFrame();
    String printFrameResult();
}
