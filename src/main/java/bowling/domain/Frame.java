package bowling.domain;

public class Frame {
    public Scores getScores() {
        return scores;
    }

    private Scores scores;

    public Frame() {
        this.scores = new Scores();
    }

    public boolean isNextFrame() {
        return this.scores.nextFrame();
    }

    public void add(Score score) {
        this.scores.add(score);
    }

    public void addNormalFrame(int numberOfPin) {
        this.scores.checkBeforeAddNormal(numberOfPin);
        this.scores.add(new Score(numberOfPin));
    }

    public void addFinalFrame(int numberOfPin) {
        this.scores.checkBeforeAddFinal(numberOfPin);
        this.scores.add(new Score(numberOfPin));
    }

    @Override
    public String toString() {
        return "Frame{" +
                "scores=" + scores +
                '}';
    }
}
