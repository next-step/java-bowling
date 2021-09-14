package bowling.model;

public interface Round {
    int STRIKE = 10;
    int GUTTER = 0;
    int FIRST_TRY = 1;
    GameResult play(int totalPoint, int tryCount);
    Round next(boolean isBonusRound, GameResult beforeResult);
    boolean isBonusRound();
    boolean isStrike();
    boolean giveBonus();
    GameResult findResult(int point, int tryCount);
    GameResult isStrikeOrSpare(int tryCount);
}
