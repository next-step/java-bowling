package bowling.domain.frame;

import java.util.List;

public class FinalFrame extends Frame {
    public FinalFrame(List<Score> scores) {
        this.scores = new FinalScores(scores);
    }

    public FinalFrame() {
        this.scores = new FinalScores();
    }

    @Override
    public int frameScore() {
        return scores.transSpareScores()
                .stream()
                .mapToInt(score -> score.getScore())
                .sum();
    }
}
