package bowling.domain;

import bowling.domain.state.Ready;
import bowling.domain.state.State;

public class Frame {
    private static final int ZERO = 0;
    private Scores scores;
    private State state;

    public State getState() {
        return state;
    }

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

    public boolean isNextFrame() {
        return state.isFinish();
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


    public boolean isCountOfStrike() {
        return this.scores.countOfSign(Sign.STRIKE) > 1;
    }

    public boolean isStrike() {
        return state.isStrike();
    }

    public boolean isSpare() {
        return state.isSpare();
    }

    public int sumScore() {
        return scores.sum();
    }

    public boolean isNumberOfTryZero() {
        return scores.numberOfTry() == ZERO;
    }

    public int calculateDoubleStrike() {
        return state.doubleStrikeBonusScore();
    }

    public int calculate() {
        return state.getScore();
    }

    public boolean isEnableCalculate() {
        return state.isEnableCalculate();
    }

    public int calculateSpare() {
        return state.spareBonusScore();
    }

    public int calculateSingleStrike() {
        return state.strikeBonusScore();
    }

    public int getScoresSize() {
        return scores.numberOfTry();
    }
}


