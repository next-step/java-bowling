package bowling.domain;

public class FinalFrame extends Frame {
    private Scores scores;

    public FinalFrame(Frame frame) {
        this.scores = frame.getScores();
    }

    public void add(Score numberOfPin) {
        this.scores.checkBeforeAddFinal(numberOfPin);
        this.scores.add(numberOfPin);
    }

    public String getSigns() {
        return scores.getSigns();
    }

    public int firstScore() {
        return scores.firstScore();
    }

    public int sumScore() {
        return scores.sum();
    }

    public int sumStrikeScore() {
        return scores.firstScore() + scores.secondScore();
    }

    public boolean isCountOfStrike() {
        return this.scores.countOfSign(Sign.STRIKE) > 1;
    }
}
