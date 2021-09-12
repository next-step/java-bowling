package bowling.model;

public interface Round {
    int BEFORE_FINAL_ROUND = 9;
    int SECOND_TRY = 2;

    BowlingResult play(int totalPoint, int tryCount, BowlingResult beforeResult);
    Round next();
    boolean isSkipNextRound();
    boolean isBonus();
}
