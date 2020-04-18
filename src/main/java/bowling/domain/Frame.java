package bowling.domain;

public class Frame {
    private static final int ZERO = 0;
    private Scores scores;

    public Frame() {
        this.scores = new Scores();
    }

    public String getSigns() {
        return scores.getSigns();
    }

    public Scores getScores() {
        return scores;
    }

    public int getScoresSize() {
        return scores.numberOfTry();
    }

    public boolean isNextFrame() {
        return this.scores.nextFrame();
    }

    public void addFrame(int numberOfPin, boolean isFinalFrame) {
        if (isFinalFrame) {
            this.scores.checkBeforeAddFinal(numberOfPin);
        }
        if (!isFinalFrame) {
            this.scores.checkBeforeAddNormal(numberOfPin);
        }
        this.scores.add(numberOfPin);
    }

    public boolean isEndGame() {
        return this.scores.isEndGame();
    }

    @Override
    public String toString() {
        return scores.toString();
    }

    public boolean isStrike() {
        return this.getSigns().contains(Sign.STRIKE.getSign());
    }

    public boolean isCountOfStrike() {
        return this.scores.countOfSign(Sign.STRIKE) > 1;
    }

    public boolean isSpare() {
        return this.getSigns().contains(Sign.SPARE.getSign());
    }

    public boolean isMiss() {
        return !isStrike() && !isSpare();
    }

    public int firstScore() {
        return scores.firstScore();
    }

    public int sumScore() {
        return scores.sum();
    }

    public boolean isNumberOfTryZero() {
        return getScoresSize() == ZERO;
    }

    public int sumStrikeScore() {
        return scores.firstScore() + scores.secondScore();
    }
}
