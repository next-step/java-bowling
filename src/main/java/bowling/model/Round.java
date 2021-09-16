package bowling.model;

import java.util.List;

public interface Round {
    int FIRST_TRY = 1;

    GameResult play(Point pinCount, int tryCount);
    void next(List<Round> rounds, GameResult beforeResult);
    int calcMaxTryCount();
    GameResult findResult(Point point, int tryCount);
    GameResult isStrikeOrSpare(int tryCount);
}
