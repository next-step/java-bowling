package bowling.engine;

public interface Frame extends FirstClassList<Shot> {
    Frame second(Shot second);
    Frame third(Shot second);

    Sequence sequence();
    boolean isClear();
    boolean completed();
    Score score();
}
