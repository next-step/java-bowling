package bowling.domain;

import bowling.domain.state.State;

public class NormalFrame extends Frame {
    private Scores scores;
    private State state;

    public void add(Score score) {
        this.scores.add(score);
    }

    public NormalFrame(Frame frame) {
        this.scores = frame.getScores();
        this.state = frame.getState();
    }

    public String getSigns() {
        return scores.getSigns();
    }

    public boolean isStrike() {
        return state.isStrike();
    }

    public boolean isSpare() {
        return state.isSpare();
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
}
