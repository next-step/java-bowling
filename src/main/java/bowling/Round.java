package bowling;

public interface Round {
    int BEFORE_FINAL_ROUND = 9;
    int SECOND_TRY = 2;

    BowlingResult play(int totalPoint, int tryCount);
    Round next(BowlingResult roundResult, int index, int tryCount);
    boolean isSkipNextRound(int tryCount, BowlingResult roundResult, boolean isBonus);
    boolean isBonus(boolean isBonus, BowlingResult roundResult);
}
