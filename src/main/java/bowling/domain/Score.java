package bowling.domain;

public abstract class Score {

    private static final int GAME_STEP = 1;

    protected int firstScore;
    protected int secondScore;
    protected int bonusScore;
    protected int sumScore;

    public Score(Frame frame) {
        this.firstScore = frame.firstPin.falledPins();
        this.secondScore = frame.secondPin.falledPins();
        this.bonusScore += 0;
        this.sumScore = firstScore + secondScore;
    }

    public String getCurrentScore() {
        return Integer.toString(firstScore + secondScore);
    }

    public String getCurrentSumScore() {
        return Integer.toString(getSumScore());
    }

    public int getScore() {
        return firstScore + secondScore;
    }

    public int getFirstScore() {
        return firstScore;
    }

    public int getBonusScore() {
        return bonusScore;
    }

    public int getSumScore() {
        return sumScore;
    }

    public void setSumScore(int sumScore) {
        this.sumScore = sumScore;
    }

    public void calculateAdditionalScore(Score nextScore) {
        addBonusNumber(nextScore);
    }

    public abstract void addBonusNumber(Score nextScore);

    @Override
    public String toString() {
        return Integer.toString(sumScore);
    }

    public abstract void reset();

}
