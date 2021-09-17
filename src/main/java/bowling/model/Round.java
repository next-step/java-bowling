package bowling.model;

public interface Round {
    int FIRST_TRY = 1;

    State play(Point pinCount, int tryCount);
    Round next(State beforeResult);
    int calcMaxTryCount();
    State findResult(Point point, int tryCount);
    State isStrikeOrSpare(int tryCount);
}
