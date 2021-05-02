package bowling.domain.frame;

public class NormalFrame extends Frame {
    
    public NormalFrame() {
        this.scores = new NormalScores();
    }

    @Override
    public int frameScore() {
        return 0;
    }
}
