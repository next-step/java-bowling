package bowling.domain.frame;

public interface Frame {

    int MAX_FRAME_NUMBER = 10;

    Frame bowl(final int pinCount);

    Frame createNext();

    Frame getNext();

    boolean isFinish();
}
