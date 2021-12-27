package bowling.engine;

public interface Frame extends FirstClassList<Shot> {
    int NUMBER_OF_PINS = 10;
    int NUMBER_OF_SHOT = 2;
    int NUMBER_OF_FINAL_SHOT = 3;

    Frame second(Shot second);
    Frame third(Shot second);

    Sequence sequence();
    boolean isClear();
    boolean completed();
    Score score();
}
