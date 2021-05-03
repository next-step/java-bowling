package bowling.domain.frame;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FinalFrame extends Frame {
    public FinalFrame(List<Score> scores) {
        this.scores = new FinalScores(scores);
    }

    public FinalFrame() {
        this.scores = new FinalScores();
    }

    @Override
    public Optional<Integer> frameScore() {
        if (scores.getScores().isEmpty() || !isFinished()) {
            return Optional.empty();
        }
        return Optional.of(scores.transSpareScores()
                .stream()
                .mapToInt(score -> score.getScore())
                .sum());
    }

    @Override
    public Optional<List<Score>> getTwoScores() {
        if (this.scores.getScores().size() >= 2) {
            List<Score> result = new ArrayList<>();
            result.add(this.scores.transSpareScores().get(0));
            result.add(this.scores.transSpareScores().get(1));
            return Optional.of(result);
        }
        return Optional.empty();
    }
}
