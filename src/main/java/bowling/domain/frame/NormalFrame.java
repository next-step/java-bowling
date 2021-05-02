package bowling.domain.frame;

import java.util.List;
import java.util.Optional;

public class NormalFrame extends Frame {

    public NormalFrame(List<Score> scores) {
        this.scores = new NormalScores(scores);
    }

    public NormalFrame() {
        this.scores = new NormalScores();
    }

    @Override
    public Optional<Integer> frameScore() {
        return null;
    }


}
