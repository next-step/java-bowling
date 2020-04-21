package bowling.domain.state;

import bowling.domain.Score;

public class Miss extends Finished {
    private Score firstScore;
    private Score secondScore;

    public Miss(Score firstScore, Score secondScore) {
        this.firstScore = firstScore;
        this.secondScore = secondScore;
    }

    public Miss(Score firstScore) {
        this.firstScore = firstScore;
    }

    @Override
    public boolean isEnableCalculate() {
        return true;
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
    public int getScore() {
        return getFirstScore() + getSecondeScore();
    }
}
