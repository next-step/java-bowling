package bowling.domain;

public class FinalFrame {
    private Scores scores;

    public FinalFrame() {
        this.scores = new Scores();
    }

    public FinalFrame(Frame frame) {
        add(frame.getScores());
    }

    public boolean isEndFinalFrame() {
        return this.scores.isEndFinalFrame();
    }

    public void add(Score score) {
        this.scores.add(score);
    }

    public void add(int numberOfPin) {
        this.scores.checkBeforeAddFinal(numberOfPin);
        this.scores.add(new Score(numberOfPin));
    }

    @Override
    public String toString() {
        return "FinalFrame{" +
                "scores=" + scores +
                '}';
    }

    public void add(Scores scores) {
        this.scores = scores;
    }
}
