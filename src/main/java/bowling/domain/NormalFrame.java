package bowling.domain;

import bowling.domain.state.State;

public class NormalFrame extends Frame {
    private static final int ZERO = 0;
    private static final int BONUS = 10;
    private static final int DOUBLE_BONUS = 20;
    private Scores scores;
    private State state;

    public void add(Score score) {
        this.scores.add(score);
    }

    public NormalFrame(Frame frame) {
        this.scores = frame.getScores();
        this.state = frame.getState();
    }

    public Scores getScores() {
        return scores;
    }

    public String getSigns() {
        return scores.getSigns();
    }

    public int sumScore() {
        return scores.sum();
    }

    public boolean isNumberOfTryZero() {
        return scores.numberOfTry() == ZERO;
    }

    public boolean isStrike() {
        return state.isStrike();
    }

    public boolean isSpare() {
        return state.isSpare();
    }

    public int calculateSingleStrike(int sumScore) {
        return sumScore + BONUS + state.getScore();
    }

    public int calculateDoubleStrike(int sumScore) {
        return sumScore + DOUBLE_BONUS + state.getFirstScore();
    }

    public int calculateSpare(int sumScore) {
        return sumScore + BONUS + state.getFirstScore();
    }

    public int calculate(int sumScore) {
        return state.getScore() + sumScore;
    }

    public boolean isEnableCalculate() {
        return state.isEnableCalculate();
    }

    public boolean isCountOfStrike() {
        return this.scores.countOfSign(Sign.STRIKE) > 1;
    }
}
