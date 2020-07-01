package bowling.domain;

public abstract class Score {

    private static final int GAME_STEP = 1;

    protected Pins pins;
    protected int firstScore;
    protected int secondScore;
    protected int score;
    protected int bonusScore;
    protected int sumScore;

    public Score(Pins pins) {
        this.pins = pins;
        this.firstScore = pins.getFirstPin().falledPins();
        this.secondScore = pins.getSecondPin().falledPins();
        this.score = firstScore + secondScore;
        this.bonusScore += 0;
        this.sumScore = score;
    }

    public String getCurrentScore() {
        return Integer.toString(score);
    }

    public String getCurrentSumScore() {
        return Integer.toString(getSumScore());
    }

    public int getSumScore() {
        return sumScore;
    }

    public void calculateAdditionalScore(Score nextScore) {
/*        if (this.pins.isFinal()) {
            nextScore = new ScoreNormal(new Pins(pins.getThirdPin(), new Pin()));
        }*/

        addBonusNumber(nextScore);

    }

    public abstract void addBonusNumber(Score nextScore);

    @Override
    public String toString() {
        return Integer.toString(sumScore);
    }

    public abstract void reset();

}
