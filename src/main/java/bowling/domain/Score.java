package bowling.domain;

public abstract class Score {

    private static final int MAX_LIMIT_BONUS = 10;

    protected Pins pins;
    protected State state;
    protected int bonusScore;
    protected int sumScore;

    public Score(Frame frame) {
        this.state = frame.checkState();
        this.pins = frame.getPins();
        this.bonusScore = 0;
        this.sumScore = 0;
    }

    protected int getScore() {
        return pins.getFirstFallenPins() + pins.getSecondFallenPins();
    }

    protected int getNextScore() {
        return getScore();
    }

    public String getCurrentScoreString() {
        return Integer.toString(
                getScore()
        );
    }

    public String getCurrentSumScore() {
        return Integer.toString(getSumScore());
    }

    protected int getFirstScore() {
        return pins.getFirstFallenPins();
    }

    protected boolean isFirstStrike() {
        return pins.getFirstFallenPins() == MAX_LIMIT_BONUS;
    }

    protected int getSecondScore() {
        return pins.getSecondFallenPins();
    }

    protected int getThirdScore() {
        return pins.getThirdFallenPins();
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
