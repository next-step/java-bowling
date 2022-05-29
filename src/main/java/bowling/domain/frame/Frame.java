package bowling.domain.frame;

public interface Frame {

    Frame bowling(int pins);

    Frame next(int pins);

    int round();

    boolean isFinalFrame();

    boolean isFinishBowling();

    String partitionPins();
}
