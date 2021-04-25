package bowling.domain.frame;

public interface Frame {
    Frame bowl(int pitch);
    boolean isFinished();
    Frame getNext();
}
