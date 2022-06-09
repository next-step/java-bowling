package bowling.domain.frame;

public interface Frame {
    int score();

    int validateMoveToNextIndex();

    boolean equal(int index);
}
