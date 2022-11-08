package bowling;

public interface Frame {

    Frame bowl(int countOfPins);

    boolean isFinished();

    boolean isFinalFrame();

    int firstScore();

    int secondScore();
}
