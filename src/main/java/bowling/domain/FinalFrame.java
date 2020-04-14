package bowling.domain;

public class FinalFrame extends Frame{
    private Scores scores;

    public FinalFrame() {
        this.scores = new Scores();
    }

    public FinalFrame(Frame frame) {
        this.scores = frame.getScores();
    }

    public boolean isEndFinalFrame() {
        return this.scores.isEndFinalFrame();
    }
}
