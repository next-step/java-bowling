package bowling.engine;

public interface Result {
    Result next(Shot shot);
    Score score();

    boolean completed();

    Shots shots();
    Bonus bonus();
}
