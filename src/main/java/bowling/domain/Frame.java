package bowling.domain;

public interface Frame {
    int score();

    int validateMoveToNextIndex();

    boolean notLastFrame();
}
