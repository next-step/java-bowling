package bowling.domain;

import bowling.domain.state.Ready;
import bowling.domain.state.State;

public class Frame {
    private static final int ZERO = 0;
    private static final int BONUS = 10;
    private static final int DOUBLE_BONUS = 20;
    private Scores scores;

    private State state;

    public Frame() {
        this.scores = new Scores();
        this.state = new Ready();
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
        return this.state.isFinish();
    }

    public void addFrame(Score numberOfPin, boolean isFinalFrame) {
        if (isFinalFrame) {
            this.scores.checkBeforeAddFinal(numberOfPin);
        }
        if (!isFinalFrame) {
            this.scores.checkBeforeAddNormal(numberOfPin);
        }
        this.scores.add(numberOfPin);
        this.state = state.bowl(numberOfPin);
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

    public int calculateMissCase() {
        return sumScore();
    }

    public int calculateSingleStrike(int sumScore) {
        return sumScore + BONUS + sumStrikeScore();
    }

    public int calculateDoubleStrike(int sumScore) {
        return sumScore + DOUBLE_BONUS + firstScore();
    }

    public int calculateSpare(int sumScore) {
        return sumScore + BONUS + firstScore();
    }
}
