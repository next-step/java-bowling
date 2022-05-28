package bowling.domain.frame;

public interface Frame {

    Frame bowling(int count);

    Frame next(int count);

    int round();

    int totalCount();

    boolean isFinalFrame();

    boolean isFinishBowling();
}
