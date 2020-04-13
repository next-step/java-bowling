package bowling.domain;

public class NormalFrame  {
    private Scores scores;

    public Scores getScores() {
        return scores;
    }

    public String getSigns() {
        return getScores().getSigns();
    }

    public NormalFrame() {
        this.scores = new Scores();
    }

    public NormalFrame(Frame frame) {
        this.scores = frame.getScores();
    }

    public boolean isNextFrame() {
        return this.scores.nextFrame();
    }

    public void add(Score score) {
        this.scores.add(score);
    }

    public void add(int numberOfPin) {
        this.scores.checkBeforeAddNormal(numberOfPin);
        this.scores.add(new Score(numberOfPin));
    }
}
