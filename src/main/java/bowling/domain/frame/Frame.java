package bowling.domain.frame;

public interface Frame extends Iterable<Frame> {

    Frame bowl(int numberOfPins);
    Frame getNextFrame();
    int getFrameNumber();
    int getScore();
    int getFirstNumberOfPin();
    boolean isEnd();

}
