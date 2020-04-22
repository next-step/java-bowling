package bowling.domain.state;

import bowling.domain.Score;

public class NextBowl extends Playing {
    private static final int BONUS = 10;
    private static final int DOUBLE_BONUS = 20;
    private Score firstScore;

    public NextBowl(Score score) {
        this.firstScore = score;
    }

    @Override
    public State bowl(Score score) {
        if(this.firstScore.isSpare(score)){
            return new Spare(this.firstScore, score);
        }
        return new Miss(firstScore, score);
    }

    @Override
    public boolean isFinish() {
        return false;
    }

    @Override
    public int getScore() {
        return super.getScore();
    }

    @Override
    public int getFirstScore() {
        return firstScore.getScore();
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
