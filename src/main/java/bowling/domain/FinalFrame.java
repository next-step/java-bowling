package bowling.domain;

public class FinalFrame extends Frame {
    private Scores scores;

    public FinalFrame(Frame frame) {
        this.scores = frame.getScores();
    }

    public void add(Score score) {
        this.scores.add(score);
    }

    public void add(int numberOfPin) {
        this.scores.checkBeforeAddFinal(numberOfPin);
        this.scores.add(new Score(numberOfPin));
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
