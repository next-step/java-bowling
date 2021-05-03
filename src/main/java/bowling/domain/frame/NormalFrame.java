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
        if (scores.getScores().isEmpty() || !isFinished()) {
            return Optional.empty();
        }

        int frameScore = scores.transSpareScores()
                .stream()
                .mapToInt(score -> score.getScore())
                .sum();

        if (scores.getScores().contains(Score.SPARE) && nextFrame.getOneScore().isPresent()) {
            frameScore += nextFrame.getOneScore()
                    .get()
                    .getScore();
        }
        if (scores.getScores().contains(Score.STRIKE) && nextFrame.getTwoScores().isPresent()) {
            frameScore += nextFrame.getTwoScores()
                    .get()
                    .stream()
                    .mapToInt(score -> score.getScore())
                    .sum();
        }
        return Optional.of(frameScore);
    }


}
