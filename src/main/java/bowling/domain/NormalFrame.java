package bowling.domain;

public class NormalFrame extends Frame {
    private Scores scores;

    public NormalFrame() {
        this.scores = new Scores();
    }

    public NormalFrame(Frame frame) {
        this.scores = frame.getScores();
    }

    public Scores getScores() {
        return scores;
    }
}
