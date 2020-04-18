package bowling.domain;

public class NormalFrame extends Frame {
    private Scores scores;

    public void add(Score score) {
        this.scores.add(score);
    }

    public NormalFrame(Frame frame) {
        this.scores = frame.getScores();
    }

    public Scores getScores() {
        return scores;
    }

    public boolean isNextFrame() {
        return this.scores.nextFrame();
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
}
