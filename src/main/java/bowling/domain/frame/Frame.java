package bowling.domain.frame;

public interface Frame {
    int FIRST_FRAME_NO = 1;
    int LAST_FRAME_NO = 10;
    Frame bowl(int pitch);
    boolean isFinished();
    Frame getNext();
}
