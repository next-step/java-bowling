package bowling.model;

import java.util.List;

public interface Round {
    int FIRST_TRY = 1;

    State play(Point pinCount, int tryCount);
    void next(List<Round> rounds, State beforeResult);
    int calcMaxTryCount();
    State findResult(Point point, int tryCount);
    State isStrikeOrSpare(int tryCount);
}
