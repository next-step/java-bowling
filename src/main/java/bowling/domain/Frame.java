package bowling.domain;

public interface Frame {
    int score();

    Frame validateMoveToNext(Board board);

    boolean notLastFrame();
}
