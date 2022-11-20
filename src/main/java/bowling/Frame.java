package bowling;

public interface Frame {

    Frame bowl(Pin falledPins);

    Frame nextFrame();

    boolean isFinalFrame();

    boolean isFinished();

    Score getScores();

    String getDesc();

    Score calculateAdditionalScore(Score beforeScore);
}
