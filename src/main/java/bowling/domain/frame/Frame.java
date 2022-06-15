package bowling.domain.frame;

public interface Frame {
    int attempt();

    int validateMoveToNextIndex();

    boolean equal(int index);

    int index();

    Scores scores();
}
