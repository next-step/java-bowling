package bowling.domain.state;

import bowling.domain.Score;

public class Spare extends Finished {
    private static final int BONUS = 10;
    private static final int DOUBLE_BONUS = 20;
    private Score firstScore;
    private Score secondScore;

    public Spare(Score firstScore, Score secondScore) {
        this.firstScore = firstScore;
        this.secondScore = secondScore;
    }

    @Override
    public int getScore() {
        return getFirstScore() + getSecondeScore();
    }

    @Override
    public boolean isEnableCalculate() {
        return false;
    }

    @Override
    public int getFirstScore() {
        return firstScore.getScore();
    }

    @Override
    public int getSecondeScore() {
        return secondScore.getScore();
    }

    @Override
    public boolean isSpare() {
        return true;
    }

    @Override
    public int spareBonusScore() {
        return BONUS + getFirstScore();
    }

    @Override
    public int strikeBonusScore() {
        return BONUS + getScore();
    }

    @Override
    public int doubleStrikeBonusScore() {
        return DOUBLE_BONUS + getFirstScore();
    }
}
