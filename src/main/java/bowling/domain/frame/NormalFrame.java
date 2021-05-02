package bowling.domain.frame;

import java.util.List;

public class NormalFrame extends Frame {
    
    public NormalFrame(List<Score> scores) {
        this.scores = new NormalScores(scores);
    }

    public NormalFrame() {
        this.scores = new NormalScores();
    }

    @Override
    public int frameScore() {
        return 0;
    }
}
