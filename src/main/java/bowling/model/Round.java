package bowling.model;

public interface Round {
    BowlingResult play(int totalPoint, int tryCount);
    Round next(boolean isBonusRound, BowlingResult beforeResult);
    boolean isBonusRound();
    boolean isStrike();
    boolean giveBonus();
}
