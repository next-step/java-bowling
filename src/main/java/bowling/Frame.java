package bowling;

public interface Frame {

    Frame bowl(Pin falledPins);

    Frame nextFrame();

    boolean isFinalFrame();

    boolean isFinished();

    int getScores();

    String getDesc();

    int calculateAdditionalScore(Score beforeScore);
}
