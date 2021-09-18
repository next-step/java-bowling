package bowling.model;

public interface Frame {
    boolean isOver();

    void record(ShotResult shotResult);
}
