package bowling.domain.frame;

public class FinalFrame extends Frame {
    public FinalFrame() {
        this.scores = new FinalScores();
    }

    @Override
    public int frameScore() {
        return 0;
    }
}
