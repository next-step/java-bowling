package bowling.engine;

public interface Frame {
    Frame nextShot(Shot shot);

    Sequence sequence();

    boolean isFinal();
    Result result();
}
