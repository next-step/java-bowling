package bowling;

public interface Frame {

    Frame bowl(Pin falledPins);

    Frame nextFrame();

    boolean isFinalFrame();

    boolean isFinished();

    public Score getScores();
}
