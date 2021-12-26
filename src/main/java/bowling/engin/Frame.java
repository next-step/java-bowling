package bowling.engin;

public interface Frame extends FirstClassList<Shot> {
    Frame second(Shot second);

    Sequence sequence();
    boolean isClear();
    boolean completed();
    // Score score(); // todo implement in step3
}
